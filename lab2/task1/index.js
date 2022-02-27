$(document).ready(function () {
    console.log("ready!");

    console.log('changed');
    $('#image-input').on('change', function () {
        let fileList = this.files;
        if (fileList.length > 0) {
            let file = fileList[0];
            addImage(file);
        }
    });
});

function getExtension(filename) {
    let parts = filename.split('.');
    return parts[parts.length - 1];
}

function isImage(filename) {
    let ext = getExtension(filename);
    switch (ext.toLowerCase()) {
        case 'jpg':
            return true;
    }

    return false;
}

function addImage(file) {
    if (!isImage(file.name)) {
        return;
    }

    let imageTag = $('#image');
    if (imageTag.length === 0) {
        imageTag = document.createElement('img')
        $(imageTag).attr('id', 'image');
        $(imageTag).attr('class', 'd-block m-auto w-100 h-100');
        $(imageTag).attr('style', 'object-fit: contain;');
    }

    let fr = new FileReader();
    fr.onload = function () {
        $(imageTag).attr('src', fr.result);
    }

    fr.readAsDataURL(file);
    console.log(imageTag);

    $('#image-wrapper').append(imageTag);
}
