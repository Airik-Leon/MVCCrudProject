
var list = ["H", "e", "l", "l", "o", " ", "I","n","t", "e", "r", "n", "e", "t"];
var counter = 0;
var elem = document.getElementById("change");
var interval = setInterval(change, 100);
function change() {
  elem.innerHTML +=list[counter];
  counter++;
  if (counter >= list.length) {
    counter = 0;
    clearInterval(interval);
  }
}
