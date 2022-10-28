function isPalindrome(str) {
    var reversed = '';
    for (var _i = 0, str_1 = str; _i < str_1.length; _i++) {
        var char = str_1[_i];
        reversed = char + reversed;
    }
    return reversed === str;
}
console.log(isPalindrome('dad'));
