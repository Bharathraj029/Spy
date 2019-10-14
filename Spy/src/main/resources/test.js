var elements=new Array();
var $=jQuery.noConflict();
$(document).ready(function(){

  $("input").click(function(){

    $.each(this.attributes,function(){
    

      if(this.name=="id"){
        
        fieldName(this.value);
        $.getId($(this),elements,this.value);
      }else if(this.name=="name"){
   
          $.getName($(this),elements,this.value);
      }else if(this.name=="class"){
         
          $.clasName($(this),elements,this.value);
      }


      else if(!(this.name=="id" || this.name=="name" || this.name=="type" || this.className=="class") && !(this.value=="") ){
    
    
        var val=doubleQuote(this.value);
        
          var xpath ='//*[@' +this.name+ '='     +val+    ']';
          checkCntXpath(xpath,this.value,this.name);
      }else{

      }
   
     });
   
     
	 if(elements.length>0){
      elements.splice(0,elements.length);
      return true;
      }
     });

  $("button").mouseover(function(e){

    e.preventDefault();
    $.each(this.attributes,function(){


      if(this.name=="id"){
        
        fieldName(this.value);
        $.getId($(this),elements,this.value);
      }else if(this.name=="name"){
   
          $.getName($(this),elements,this.value);
      }else if(this.name=="class"){
         
          $.clasName($(this),elements,this.value);
      }


      else if(!(this.name=="id" || this.name=="name" || this.name=="type" || this.className=="class") && !(this.value=="") ){
    
    
        var val=doubleQuote(this.value);
        
          var xpath ='//*[@' +this.name+ '='     +val+    ']';
          checkCntXpath(xpath,this.value,this.name);
      }else{

      }

    });
    localStorage.setItem('items',elements);
     console.log(elements);
   if(elements.length>0){
      elements.splice(0,elements.length);
      return true;
      }


  })
  
});


$(document).ready(function(){
	$.getId=function(idOfElem,elements,value){
	elements.push("{Id:"+value+"}");

	};
});

$(document).ready(function(){
	$.getName=function(nameOfElem,elements,nameVal){
    elements.push("{Name:"+nameVal+"}");
	};
});

$(document).ready(function(){
  $.clasName=function(nameOfElem,elements,nameVal){
   

    elements.push("{class:"+nameVal+"}");
  };
});


function fieldName(value1){

if(value1!=""){
  
      var label = $("label[for='"+value1+"']");
    
      
      elements.push("{Field:"+label.text()+"}");
}else {
var val=" ";
  elements.push("{Field:"+val+"}");
}

       

}

function checkCntXpath(xpathToExecute,val,nam){
  

  try{
  
  var nodesSnapshot = document.evaluate(xpathToExecute, document, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null );
  
  if(nodesSnapshot.snapshotLength==1){
    elements.push(xpathToExecute);
  }
  }catch(err){
  
    var xpathReval=addQuotes(val);
    var xpath ='//*[@' +nam+ '='     +xpathReval+    ']';
   checkCntXpath(xpath,val,nam);
  }
  
}

function addQuotes(value){
    var quotedVar = "\'" + value + "\'";
    return quotedVar;
}

function doubleQuote(value){

var doubQuot="\"" +value+ "\"";
return doubQuot;

}