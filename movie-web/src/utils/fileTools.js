export const getfileByBase64 = (file, callback) => {
    var reader = new FileReader();
    // 传入一个参数对象即可得到基于该参数对象的文本内容
    reader.readAsDataURL(file);
    reader.onload = function (e) {
      // target.result 该属性表示目标对象的DataURL
      callback(e.target.result);
    };
  };

export const getFileExtension = (file) =>{
    return file.name.substring(file.name.lastIndexOf('.') + 1);
  }