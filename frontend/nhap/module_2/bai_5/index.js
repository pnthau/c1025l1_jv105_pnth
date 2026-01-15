function k_arr(n, k, prefix) {
    if (n === 0) {
        console.log(prefix);
        return;
    }
    for (let i = 0; i < k.length; i++) {
        k_arr(n - 1, k, prefix + i);
    }
}
k_arr(2, [0, 1, 2], "");