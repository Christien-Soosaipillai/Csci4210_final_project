var chart;
var height = 200
var width = 300
//DEFINE YOUR VARIABLES UP HERE


//Gets called when the page is loaded.
function init(){


  //
}

//Called when the update button is clicked
function updateClicked(){
  d3.csv('data/CoffeeData.csv',update)
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
    {
     //   for (int j = 0; j < storageVector[i].array.length; j++)       // iterates through the internal array of the class
        { 
        // result +=(( StorageVector[i].Array[j] -  StorageVector[i].min) / ( StorageVector[i].max -  StorageVector[i].min ))*  StorageVector[i].weight;
        }

    }
    
    



  //
}

// Returns the selected option in the X-axis dropdown. Use d[getXSelectedOption()] to retrieve value instead of d.getXSelectedOption()
function getXSelectedOption(){
 
}

// Returns the selected option in the X-axis dropdown. 
function getYSelectedOption(){
  
}

