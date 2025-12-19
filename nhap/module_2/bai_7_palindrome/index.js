// function palindrome(str) {
//     if (str.length <= 1) {
//         return true;
//     }
//     if (str[0] !== str[str.length - 1]) {
//         return false;
//     }
//     return palindrome(str.substring(1, str.length - 1));
// }

// palindrome("maam");

function palindrom(l, r, str) {
    if (l >= r) {
        return true;
    }
    if (str[l] !== str[r]) {
        return false;
    }
    return palindrom(l + 1, r - 1, str);
}
palindrom(0, 3, "maam");