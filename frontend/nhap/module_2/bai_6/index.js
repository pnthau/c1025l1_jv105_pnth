function findRegionSizeFrom(x, y, grid, visited) {
    const boundary = {
        minX: 0,
        minY: 0,
        MaxX: grid.length,
        MaxY: grid[0].length,
    }
    const isOutXGrid = x < boundary.minX || x >= boundary.MaxX;
    const isOutYGrid = y < boundary.minY || y >= boundary.MaxY;
    if (isOutXGrid || isOutYGrid || grid[x][y] === 0 || visited[x][y] === true) {
        return 0;
    }
    visited[x][y] = true;
    let size = 1;
    const directions = [
        [-1, 0], //up
        [1, 0], //down
        [0, -1], //left
        [0, 1], //right
        [1, -1], //top-right
        [-1, -1], //top-left
        [1, 1],//bot-right
        [-1, 1] //bot-left
    ];
    for (const [x0, y0] of directions) {
        size += findRegionSizeFrom(x0 + x, y0 + x, grid, visited);
    }
    return size;
}

function maxSizeConnectedCells(grid, visited) {
    let maxSize = 0;
    for (let i = 0; i < grid.length; i++) {
        for (let j = 0; j < grid[0].length; j++) {
            if (visited[i, j] === true) {
                continue;
            }
            const curSize = findRegionSizeFrom(i, j, grid, visited);
            if (curSize > maxSize) {
                maxSize = curSize;
            }
        }
    }
    return maxSize;
}

maxSizeConnectedCells([[1, 1, 0, 0], [0, 1, 1, 0], [0, 0, 1, 0], [1, 0, 0, 0]], [[false, false, false, false], [false, false, false, false], [false, false, false, false], [false, false, false, false]]);