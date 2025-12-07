const MAX = 100;
const MIN = 0;
function generateNumber() {
    return Math.round(MIN + Math.random() * (MAX - MIN));
}
function bubbleSortASC(arrayToSort) {
    for (let passNumber = 0; passNumber < arrayToSort.length; passNumber++) {
        for (let i = 0; i < arrayToSort.length - 1; i++) {
            if (arrayToSort[i] > arrayToSort[i + 1]) {
                let temp = arrayToSort[i];
                arrayToSort[i] = arrayToSort[i + 1];
                arrayToSort[i + 1] = temp;
            }
        }
    }
}
function sortArraysTogether(firstArray, secondArray) {
    let currentIndexFirstArray = firstArray.length - 1;
    let currentIndexSecondArray = secondArray.length - 1;
    let countLength = currentIndexFirstArray + currentIndexSecondArray + 1;
    while (countLength >= 0) {
        if (currentIndexFirstArray >= 0 && firstArray[currentIndexFirstArray] > secondArray[currentIndexSecondArray]) {
            firstArray[countLength] = firstArray[currentIndexFirstArray]
            currentIndexFirstArray--;
        } else if (currentIndexSecondArray >= 0) {
            firstArray[countLength] = secondArray[currentIndexSecondArray]
            currentIndexSecondArray--;
        }
        countLength--;
    }
    return firstArray;
}
function mergeSortASC(arrayToSort) {
    if (arrayToSort.length === 1) {
        return arrayToSort;
    }

    const FACTORY_HALF = 2;
    const START_ARRAY = 0;
    const END_ARRAY = arrayToSort.length;

    const middleArray = Math.ceil((END_ARRAY + START_ARRAY) / FACTORY_HALF);
    const arrayLeft = mergeSortASC(arrayToSort.slice(START_ARRAY, middleArray), middleArray);
    const arrayRight = mergeSortASC(arrayToSort.slice(middleArray, END_ARRAY), END_ARRAY);

    const resultSorted = sortArraysTogether(arrayLeft, arrayRight);

    return resultSorted;
}
const LENGTH = 1000;
const array = Array.from({ length: LENGTH }).map(function (value, index, array) {
    return generateNumber();
});
const array1 = Array.from({ length: LENGTH }).map(function (value, index, array) {
    return generateNumber();
});
//const mergerArray = mergeSortASC(array);
//document.writeln(mergerArray);
console.time("mergerArray");
const mergerArray = mergeSortASC(array);
document.writeln(mergerArray);
console.timeEnd("mergerArray");


console.time("array1");
bubbleSortASC(array1);
document.writeln(array1);
console.timeEnd("array1");
document.writeln(array);