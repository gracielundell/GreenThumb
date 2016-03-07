var squareImg = "red";

var circleArray = [
  {
    imgSource: "carrot.png"
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
    imgSource: "dirt.png"
  }
];

// set img for circles
function createCircleDiv(circle) {
  return "<div class='circle' style='background-image:" + "url(\"img/" + circle.imgSource + "\")'></div>"
};

// create circles
$(document).ready(function() {
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

});
