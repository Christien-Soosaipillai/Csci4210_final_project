var chart;
var margin = {top: 60, right: 100, bottom: 70, left: 80},
  width = 1000- margin.left - margin.right,
  height = 700 -margin.top - margin.bottom;
var svg;
//chosen year interval value
var chosen = 0;
//actual year
var yearval = 0;
//values containing selected continent and year
var newList = [];
//values with aggrigatted value as well
var cleanList = [];
//list of continents selected
var continents = [];

//weights
var gdp_weight = 0;
var health_weight = 0;
var gdp_weight_val = 0;
var health_weight_val = 0;
var total_weight = 0;

//continents Scale
//color scale
colorArray = ["#EAA851", "#99E584", "#62FACA", "#62B3D1", "#E490BB"]; 

//color matching contenent list
dataArray = ["Americas", "Africa", "Asia", "Europe", "Oceania"];

//colourscale
var colorScale = d3.scale.ordinal()
     .domain(dataArray)
     .range(colorArray);


//GDP US values
var gdp_Us_min;
var gdp_Us_max;

//GDP International values
var gdp_national_min;
var gdp_national_max;

//Life expectancy values
var life_exp_min;
var life_exp_max;

//Health Precentage values
var health_prec_min;
var health_prec_max;



//Gets called when the page is loaded.
function init(){

  svg = d3.select("body").append("svg")
    .attr("width", width + margin.left + margin.right)
    .attr("height", height + margin.top + margin.bottom)
    .append("g")
    .attr("transform", "translate(" + margin.left + "," + margin.top + ")");

    
}



//Called when the update button is clicked to update vis
function updateClicked(){

  //Reset for all recurssive variables
  svg.selectAll("*")
	.remove();
  continents = [];
  newList = [];
  cleanList = [];
  xValue = 0;
  xScale = 0;
  xMap = 0;
  xAxis = 0;
  yValue = 0;
  yScale = 0;
  yMap = 0;
  yAxis = 0;  


  // setup x 
var xValue = function(d) { return d.Agg;}, // data -> value
    xScale = d3.scale.linear().range([0, width]), // value -> display
    xMap = function(d) { return xScale(xValue(d));}, // data -> display
    xAxis = d3.svg.axis().scale(xScale).orient("bottom");

// setup y
var yValue = function(d) { return d.lifexp;}, // data -> value
    yScale = d3.scale.linear().range([height, 0]), // value -> display
    yMap = function(d) { return yScale(yValue(d));}, // data -> display
    yAxis = d3.svg.axis().scale(yScale).orient("left");


// Define the div for the tooltip
var div = d3.select("body").append("div") 
    .attr("class", "tooltip")       
    .style("opacity", 0);

  //Year sliderChange
  var val = document.getElementById('slider').value;  
  yearval = val;
  chosen = val - 2003;
  document.getElementById('sliderStatus').innerHTML = val;
  
  


  d3.csv('data/Clean-3/cleandata.csv',function(data){
        
      //Sort by country
      var country_data_sort = d3.nest()
                                .key(function(d){return d.year;})
                                .sortKeys(d3.ascending)
                                .entries(data);  

      //Getter function call for continents                         
      getChecked();

      //Getter for weights
      health_weight = document.getElementById('health_weight'); 
      health_weight_val = health_weight.options[health_weight.selectedIndex].value;

      gdp_weight = document.getElementById('gdp_weight');
      gdp_weight_val = gdp_weight.options[gdp_weight.selectedIndex].value;
      total_weight = parseInt(gdp_weight_val) + parseInt(health_weight_val);    

      //check if data values match continents & year selected
      for(var i=0; i < data.length; i++){
          if((continents.indexOf(data[i].continent) !== -1) && data[i].year === yearval){

            newList.push(data[i]);  
          }
      }

      //normalize the data for min and max values
      cleanList = normalize(newList);
      
  
      //
      xScale.domain([0, d3.max(cleanList, xValue)+1]);
      yScale.domain([0, 95]);


      // x-axis
      svg.append("g")
        .attr("class", "x axis")
        .attr("transform", "translate(0," + height + ")")
        .call(xAxis)
        .append("text")
        .attr("class", "label")
		.transition()
		.duration(1000)
        .attr("x", width/2)
        .attr("y", 40)
        .style("text-anchor", "middle")
        .text("Aggregate Value");

      // y-axis
      svg.append("g")
        .attr("class", "y axis")
        .call(yAxis)
        .append("text")
        .attr("class", "label")
        .attr("transform", "rotate(-90)")
		.transition()
		.duration(1000)
        .attr("x", -100)
        .attr("dy", "-2.40em")
        .style("text-anchor", "middle")
        .text("Life Expectancy");

      // draw dots
      svg.selectAll(".dot")
        .data(cleanList)
        .enter().append("circle")
        .attr("class", "dot")
        .attr("r", function (d) { return scalePop(d.population)})
        .attr("cx", xMap)
        .attr("cy", yMap)
        .style("stroke","black")
        .attr("fill", function (d) { return colorScale(d.continent)})
        .on("mouseover", function(d) {  
            //mouseover to get values and insert into text field
            insert(d['country'], d['gdpus'], d['lifexp'],d['population'],d['healthpercent']);
      });
    });
}



//insert values into text fields
function insert(c_name, x, y, z, i){
    
    document.getElementById('country').value = c_name;
    document.getElementById('x_value').value = '$'+x;
    document.getElementById('y_value').value = y + ' years';
    document.getElementById('pop_value').value =z;
    document.getElementById('health_value').value =i;
}


//scale population size to get radius for the circle being plotted
//radius of cicle represents population of country
function scalePop(val){
  if(val >= 0 && val <=100000){
    val = 2;
    return val;
  }else if(val > 100000 && val <= 500000){
    val = 4;
    return val;
  }else if(val > 500000 && val <= 1000000){
    val = 5;
    return val;
  }else if(val > 1000000 && val <= 10000000){
    val = 7;
    return val;
  }else{
    val = 9;
    return val;
  }
}

//Callback for when data is loaded to
function normalize(data){


  var max = {};
  var min = {};
  var weights = {};


//this will give all the values per country 
  for(var i = 0; i < data.length; i++){
    Object.keys(data[i]).forEach(function(key,index){
        
        if(!(key in max)){
          max[key] = data[i][key];
        }
        else{
          if(max[key] < data[i][key])
            max[key] = data[i][key];
        }
         if(!(key in min)){
          min[key] = data[i][key];
        }
        else{
          if(min[key] > data[i][key])
            min[key] = data[i][key];
        }
         if(!(key in weights)){
          weights[key] = total_weight;
        }
        

    });
  }


//formula loop
//read through each data set
for(var i = 0; i < data.length; i++){
    //read through each key for each object in the data set
    Object.keys(data[i]).forEach(function(key,index){
       
      if(isNaN(Math.abs(((data[i][key] - min[key]) / (max[key] - min[key]) * weights[key])))){
       
     }else{
      //push back new aggrigate data value into data set
      data[i]["Agg"] =  Math.abs(((data[i][key] - min[key]) / (max[key] - min[key]) * weights[key]));
       
     }
       
    });
     }
  
  return data;

}


//automatically change the slider display year
function sliderChange(value){
  var slider = document.getElementById("slider").value;
  document.getElementById('sliderStatus').innerHTML = slider;
}


//check which continents are selected
function getChecked(){

  var checkedValue = null;
  var inputElements = document.getElementById('Country_check');
  for(var i=0; inputElements[i]; ++i){
    if(inputElements[i].checked){
      continents.push(inputElements[i].value);
    }
    
  }
}

