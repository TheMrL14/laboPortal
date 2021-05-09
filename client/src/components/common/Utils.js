export function FetchByteArray(file) {
    let reader = new FileReader();
    var fileByteArray = [];
    return new Promise((resolve) => {
        reader.readAsArrayBuffer(file);
        reader.onload = (evt) => {
            if (evt.target.readyState == FileReader.DONE) {
                var arrayBuffer = evt.target.result,
                    array = new Uint8Array(arrayBuffer);
                for (var i = 0; i < array.length; i++) {
                    fileByteArray.push(array[i]);
                }
            }

            resolve(fileByteArray);
        };
    });
}
