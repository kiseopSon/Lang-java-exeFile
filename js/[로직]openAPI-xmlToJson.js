//기본 파싱함수
function xmlToJson(xml) {
    // Create the return object
    var obj = {};
  
    if (xml.nodeType == 1) {
      // element
      // do attributes
      if (xml.attributes.length > 0) {
        obj["@attributes"] = {};
        for (var j = 0; j < xml.attributes.length; j++) {
          var attribute = xml.attributes.item(j);
          obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
        }
      }
    } else if (xml.nodeType == 3) {
      // text
      obj = xml.nodeValue;
    }
  
    // do children
    // If all text nodes inside, get concatenated text from them.
    var textNodes = [].slice.call(xml.childNodes).filter(function(node) {
      return node.nodeType === 3;
    });
    if (xml.hasChildNodes() && xml.childNodes.length === textNodes.length) {
      obj = [].slice.call(xml.childNodes).reduce(function(text, node) {
        return text + node.nodeValue;
      }, "");
    } else if (xml.hasChildNodes()) {
      for (var i = 0; i < xml.childNodes.length; i++) {
        var item = xml.childNodes.item(i);
        var nodeName = item.nodeName;
        if (typeof obj[nodeName] == "undefined") {
          obj[nodeName] = xmlToJson(item);
        } else {
          if (typeof obj[nodeName].push == "undefined") {
            var old = obj[nodeName];
            obj[nodeName] = [];
            obj[nodeName].push(old);
          }
          obj[nodeName].push(xmlToJson(item));
        }
      }
    }
    return obj;
  }


//요청 함수 - 자바스크립트
//함수선언 - 공공데이터 사이트(https://www.data.go.kr/) 일일 트래픽 100회
//body에 값이 들어있고, 파라미터: strtYymm= & endYymm = 수동입력 필요.
const getXMLfromAPI = () => {
    const url = 'http://apis.data.go.kr/1220000/nitemtrade/getNitemtradeList';
    const authKey = 'uDrMWcY7Ab5DDjWUaIhL4EmCOIskE4YqaZJ%2BFQo8TJvcnotpU6nOgaCjTQbzANLgd7xABL%2FI9IJJX9Vs5wYZKA%3D%3D';
    const reqURL = url + '?serviceKey=' + authKey + '&strtYymm=202201&endYymm=202212&hsSgn=1001999090&cntyCd=US';

    let getXML = fetch(reqURL, {
        headers : {
            mrethod : 'GET',
        }
    }).then(function(result){
        return result.text();
    }).then(function(xmlData){
		//가져온 XML을 JSON으로 변환
        var XmlNode = new DOMParser().parseFromString(xmlData, 'text/xml');
        console.log(xmlToJson(XmlNode));

    })
};

//함수호출
getXMLfromAPI();