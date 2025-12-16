function palindrome(str) {
    if (str.length <= 1) {
        return true;
    }
    if (str[0] !== str[str.length - 1]) {
        return false;
    }
    return palindrome(str.substring(1, str.length - 1));
}

palindrome("maam");