var arr = [10, 22, 23, 24, 55, 50];
var sum = 0;
arr.forEach(function (num) { sum += num; });
var average = sum / arr.length;
console.log(average);
