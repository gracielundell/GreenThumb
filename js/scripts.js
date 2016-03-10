var squareImg = "red";

var circleArray = [
  {
    imgSource: "dirt.png"
  },
  {
    imgSource: "artichoke.png"
  },
  {
    imgSource: "asparagus.png"
  },
  {
    imgSource: "beet.png"
  },
  {
    imgSource: "potatoes.png"
  },
  {
    imgSource: "broccoli.png"
  },
  {
    imgSource: "brussels.png"
  },
  {
    imgSource: "cabbage.png"
  },
  {
    imgSource: "cantaloupe.png"
  },
  {
    imgSource: "carrot.png"
  },
  {
    imgSource: "cauliflower.png"
  },
  {
    imgSource: "celery.jpg"
  },
  {
    imgSource: "chard.png"
  },
  {
    imgSource: "chinese-cabbage.png"
  },
  {
    imgSource: "chives.jpeg"
  },
  {
    imgSource: "corn.png"
  },
  {
    imgSource: "cucumber.png"
  },
  {
    imgSource: "dill.png"
  },
  {
    imgSource: "eggplant.png"
  },
  {
    imgSource: "endive.png"
  },
  {
    imgSource: "garlic.jpg"
  },
  {
    imgSource: "kale.png"
  },
  {
    imgSource: "kohlrabi.png"
  },
  {
    imgSource: "leeks.png"
  },
  {
    imgSource: "lettuce.png"
  },
  {
    imgSource: "lima.png"
  },
  {
    imgSource: "ocra.png"
  },
  {
    imgSource: "onions.png"
  },
  {
    imgSource: "parsley.png"
  },
  {
    imgSource: "peas.png"
  },
  {
    imgSource: "peppers.png"
  },
  {
    imgSource: "potatoes.png"
  },
  {
    imgSource: "pumpkin.png"
  },
  {
    imgSource: "radish.png"
  },
  {
    imgSource: "rhubarb.png"
  },
  {
    imgSource: "rutabaga.png"
  },
  {
    imgSource: "spinach.jpeg"
  },
  {
    imgSource: "string_beans.png"
  },
  {
    imgSource: "summer-squash.png"
  },
  {
    imgSource: "sweet-potatoes.png"
  },
  {
    imgSource: "tomato.png"
  },
  {
    imgSource: "turnip.png"
  },
  {
    imgSource: "watermelon.png"
  },
  {
    imgSource: "winter-squash.png"
  },
];

// set img for circles
function createCircleDiv(circle) {
  return "<div class='circle' style='background-image:" + "url(\"src/main/resources/public/img/" + circle.imgSource + "\")'></div>"
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
