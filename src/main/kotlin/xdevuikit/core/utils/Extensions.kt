package xdevuikit.core.utils

var <T> Array<T>.first
    set(value) = set(0, value)
    get() = get(0)

var <T> Array<T>.second
    set(value) = set(1, value)
    get() = get(1)

var IntArray.first
    set(value) = set(0, value)
    get() = get(0)

var IntArray.second
    set(value) = set(1, value)
    get() = get(1)