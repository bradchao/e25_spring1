function uploadFile(){
    console.log("debug1")
    let fileInput = document.getElementById("fileInput");
    let file = fileInput.files[0];

    let reader = new FileReader();
    reader.onload = function () {
        let base64Full = reader.result;
        console.log(base64Full);
        let base64 = base64Full.split(",")[1];
        console.log(base64.length);

        let data = {
            fileName: file.name,
            contentType: file.type,
            base64: base64
        };

        let url = "/member/test3";
        fetch(url, {
            method: "POST",
            headers: {"Content-type":"application/json"},
            body: JSON.stringify(data)
        }).then().catch();
    }

    reader.readAsDataURL(file);

}