/*"custom-select Append":*/
function loadSelect(selectedClassName, clickFunction) {
    var className = 'custom-select';

    if(selectedClassName) {
        className = selectedClassName;
    }
    
    /*"custom-select":*/
    var x, i, j, selElmnt, a, b, c;
    /*look for any elements with the class "custom-select":*/
    x = document.getElementsByClassName(className);
    for (i = 0; i < x.length; i++) {
        selElmnt = x[i].getElementsByTagName("select")[0];
        /*for each element, create a new DIV that will act as the selected item:*/
        a = document.createElement("DIV");
        a.setAttribute("class", "select-selected");
        if(selElmnt.options[selElmnt.selectedIndex] != undefined){
        	a.setAttribute("id",selElmnt.options[selElmnt.selectedIndex].id);
            a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
        }
        
        x[i].appendChild(a);
        /*for each element, create a new DIV that will contain the option list:*/
        b = document.createElement("DIV");
        b.setAttribute("class", "select-items select-hide");
        for (j = 0; j < selElmnt.length; j++) {
            /*for each option in the original select element,
            create a new DIV that will act as an option item:*/
            c = document.createElement("DIV");
            c.innerHTML = selElmnt.options[j].innerHTML;

            if(selElmnt.options[j].label==1){

                c.value = selElmnt.options[j].value;
                 
            	var onclick = document.createAttribute("onclick");
                var value = document.createAttribute("value");
            	value.value = c.value;
                onclick.value="onCustomSelect(this)"
            	
            	c.setAttributeNode(value);
                c.setAttributeNode(onclick);

            }   
            
            c.addEventListener("click", function (e) {
               /* when an item is clicked, update the original select box,
                and the selected item:*/
                var y, i, k, s, h;
                s = this.parentNode.parentNode.getElementsByTagName("select")[0];
                h = this.parentNode.previousSibling;
                for (i = 0; i < s.length; i++) {
                    if (s.options[i].innerHTML == this.innerHTML) {
                        s.selectedIndex = i;
                        h.innerHTML = this.innerHTML;
                        y = this.parentNode.getElementsByClassName("same-as-selected");
                        for (k = 0; k < y.length; k++) {
                            y[k].removeAttribute("class");
                        }
                        this.setAttribute("class", "same-as-selected");
                        if(s.options[i]!= undefined && s.options[i].id != undefined)
                        	this.setAttribute("id",s.options[i].id);
                        break;
                    }
                }
                h.click();
                if(clickFunction) {
                	window["clickFunction"] = clickFunction;
                	window["clickFunction"](this);
                }
                
            });
            
            b.appendChild(c);
        }
        x[i].appendChild(b);
        a.addEventListener("click", function (e) {
            /*when the select box is clicked, close any other select boxes,
            and open/close the current select box:*/
            e.stopPropagation();
            closeAllSelect(this);
            this.nextSibling.classList.toggle("select-hide");
            this.classList.toggle("select-arrow-active");
        });
    }
    function closeAllSelect(elmnt) {
        /*a function that will close all select boxes in the document,
        except the current select box:*/
        var x, y, i, arrNo = [];
        x = document.getElementsByClassName("select-items");
        y = document.getElementsByClassName("select-selected");
        for (i = 0; i < y.length; i++) {
            if (elmnt == y[i]) {
                arrNo.push(i)
            } else {
                y[i].classList.remove("select-arrow-active");
            }
        }
        for (i = 0; i < x.length; i++) {
            if (arrNo.indexOf(i)) {
                x[i].classList.add("select-hide");
            }
        }
    }
    /*if the user clicks anywhere outside the select box,
    then close all select boxes:*/
    document.addEventListener("click", closeAllSelect);
}

$(document).ready(function () {
    loadSelect();
    $(".more-isbn a").click(function () {
        $(this).find('i').toggleClass("rotate-icon");
        // $("a").children('i').toggleClass("rotate-icon");
    });
});

setTimeout(function(){$(".lfr-nav-item .menu-disable").removeAttr("href");
$(".lfr-nav-item .menu-disable").append('<span class="tooltiptext hidden" id="top-tooltip">Coming soon...</span>');
}, 1000);




$(document).on("mouseenter", ".lfr-nav-item .menu-disable", function() {
    // hover starts code here
	$( "#top-tooltip" ).removeClass("hidden");
});

$(document).on("mouseleave", ".lfr-nav-item .menu-disable", function() {
    // hover ends code here
	$( "#top-tooltip" ).addClass("hidden");
});


$(".tooltip-open").hover(function(){
	$( "#bottom-tooltip" ).removeClass("hidden");
    }, function(){
   $( "#bottom-tooltip" ).addClass("hidden");
});

