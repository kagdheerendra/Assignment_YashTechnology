const arr = [1, 2, 3, 4, 5];
var sum = 0;
arr.forEach(function(num) { sum += num });
var average = sum / arr.length;
console.log(average);