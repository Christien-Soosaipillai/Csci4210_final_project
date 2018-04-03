var chart;
var margin = {top: 60, right: 100, bottom: 70, left: 80},
  width = 850 - margin.left - margin.right,
  height = 370 - margin.top - margin.bottom;
var svg;
//DEFINE YOUR VARIABLES UP HERE


//java parsed values
// GDP_US_MIN=112.8493703
// GDP_US_MAX=115761.5077
// GDP_INTER_MIN=0.0
// GDP_INTER_MIN=129349.9164
// life min=41.668999
// life max=83.480003
// health care min=1.63062912
// health care max=53.0

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



// setup x 
var xValue = function(d) { return d.gdpus;}, // data -> value
    xScale = d3.scale.linear().range([0, width]), // value -> display
    xMap = function(d) { return xScale(xValue(d));}, // data -> display
    xAxis = d3.svg.axis().scale(xScale).orient("bottom");

// setup y
var yValue = function(d) { return d.lifexp;}, // data -> value
    yScale = d3.scale.linear().range([height, 0]), // value -> display
    yMap = function(d) { return yScale(yValue(d));}, // data -> display
    yAxis = d3.svg.axis().scale(yScale).orient("left");


//Called when the update button is clicked
function updateClicked(){
  d3.csv('data/Clean-3/cleandata.csv',function(data){
        
      //Sort by country
      var country_data_sort = d3.nest()
                                .key(function(d){return d.country;})
                                .entries(data);  
      
  xScale.domain([d3.min(data, xValue)-1, d3.max(data, xValue)+1]);
  yScale.domain([d3.min(data, yValue)-1, d3.max(data, yValue)+1]);

  // x-axis
  svg.append("g")
      .attr("class", "x axis")
      .attr("transform", "translate(0," + height + ")")
      .call(xAxis)
    .append("text")
      .attr("class", "label")
      .attr("x", width/2)
      .attr("y", 40)
      .style("text-anchor", "middle")
      .text("GDP US");

  // y-axis
  svg.append("g")
      .attr("class", "y axis")
      .call(yAxis)
    .append("text")
      .attr("class", "label")
      .attr("transform", "rotate(-90)")
      .attr("x", -100)
      .attr("dy", "-2.40em")
      .style("text-anchor", "middle")
      .text("Life Expectancy");

    
      console.log(country_data_sort);
      
      });
  
}

//Callback for when data is loaded
function update(data)
{
    /*
  I will Need: 
  - a data class that stores 
    - the min
    - the max
    - an array of the data (doesn't need to have country labels) 
    - the current weight of it 

   - I will need to be passed 
    - a vector of the data class so that I know how many i am dealing with here.     
  */

  
   // for (int i =0; i < StorageVector.length; i++)         // iterates through the array of classes
    //{
     //   for (int j = 0; j < storageVector[i].array.length; j++)       // iterates through the internal array of the class
        //{ 
        // result +=(( StorageVector[i].Array[j] -  StorageVector[i].min) / ( StorageVector[i].max -  StorageVector[i].min ))*  StorageVector[i].weight;
        //}

    //}
    
    



  //
}

// Returns the selected option in the X-axis dropdown. Use d[getXSelectedOption()] to retrieve value instead of d.getXSelectedOption()
function getXSelectedOption(){
 
}

// Returns the selected option in the X-axis dropdown. 
function getYSelectedOption(){
  
}

