var squareImg = "red";

var circleArray = [
  {
    imgSource: "dirt.png"
  },
  {
    imgSource: "flower.png"
  },
  {
    imgSource: "tomato.png"
  },
  {
    imgSource: "basil.png"
  },
  {
    imgSource: "potatoes.png"
  },
  {
    imgSource: "garlic.jpg"
  },
  {
    imgSource: "carrot.png"
  }
];

// set img for circles
function createCircleDiv(circle) {
  return "<div class='circle' style='background-image:" + "url(\"img/" + circle.imgSource + "\")'></div>"
};

// create box divs for plot size
function createBoxDiv(box) {
    return "<div class='box'></div>"
}

$(document).ready(function() {
  // create circles
  circleArray.forEach(function(circle) {
    var circleDiv = createCircleDiv(circle);
    $(".circles").append(circleDiv);
  });

  // set square's background img
  $(".wrapper").click(function(event) {
    $(event.target).css("background-image", squareImg);
  });

  // set squareImg to circle's img
  $(".circle").click(function(event) {
    // remove selector when new circle is clicked on
    $('.circle').removeClass("selected")

    squareImg = $(event.target).css("background-image");
    $(event.target).addClass("selected");
  });

  // INPUT FIELDS FOR PLOT SIZE
  $(".selectSize").submit(function(event) {
    var width = parseInt($("select#width").val());
    var height = parseInt($("select#height").val());
    var squareFt = width * height;
    var plotWidth = 318;
    var plotHeight = 1000;
    $(".plot").empty();
    $(".plot").css("width", plotWidth);
    for (i = 0; i < squareFt; i++) {
      $(".plot").append("<div class='box' style='width:" + (plotWidth / width) +
      "px;height:" + (plotWidth / width) + "px'></div>");
    };
    event.preventDefault();
  });

});
