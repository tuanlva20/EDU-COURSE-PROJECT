
function Validator(options) {

    //hàm kiểm tra lỗi và trả về message
    function validate(inputElement, rule) {
        var errorElement = inputElement.parentElement.querySelector('.form-message');
        var errorMessage = rule.test(inputElement.value);
        
        if (errorMessage) {
            errorElement.innerText = errorMessage;
            inputElement.classList.add("is-invalid");
        } else {
            errorElement.innerText = '';
            inputElement.classList.remove("is-invalid");
        }
    }



    // var el = document.getElementById("create");
    // el.classList.add('disabled');
    // trả về thẻ đầu tiên theo đối số truyền vào và gán vào biến formElement
    var formElement = document.querySelector(options.form);
    if (formElement) {
        var i = 0;
        //duyệt các thuộc tính cần kiểm lỗi của đối tượng options
        options.rules.forEach(function (rule) {
           
            //lấy thẻ đầu tiên trong form theo tên
            var inputElement = document.querySelector(rule.selector);
            //lấy thẻ con(span) đầu tiên thuộc thẻ cha của inputElement
         
            if (inputElement) {
                //
                inputElement.onblur = function () {
                    validate(inputElement, rule);
                }
            }
            
        })
        
    }
    
    
}

// các thuộc tính của hàm validator
//định nghĩa rules
//nguyên tắc khi có lỗi trả ra message
Validator.isRequired = function (selector) {
    return {
        selector : selector,
        test : function (value) {
            return value ? undefined : 'Bạn chưa điền thông tin';
        }
    }
}

Validator.isEmail = function (selector) {
    return {
        selector : selector,
        test : function (value) {
            var regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return regex.test(value) ? undefined : 'Email không hợp lệ';
        }
    }
}

