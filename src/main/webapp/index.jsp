<%@page contentType="text/html; charset=utf-8" isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta content="always" name="referrer">
    <title>百度一下，你就知道</title>

    <link rel="stylesheet" id="s_superplus_css_lnk" type="text/css" href="./index_files/super_min_0cb4b166.css">
    <link rel="stylesheet" type="text/css" href="./index_files/card_min_e8bcf60d.css">
    <link rel="stylesheet" href="./index_files/ubase_83c8f0ba.css">
    <link rel="stylesheet" href="./index_files/mt_min_d0e7c6d2.css">
    <link rel="stylesheet" href="./index_files/nsguide_a8cbc2e7.css">
    <link rel="stylesheet" href="./index_files/super_ext_c02dfc40.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript">
        $(function () {

            $("#su").click(function () {
                $("#ff").datagrid({
                    url: "${pageContext.request.contextPath}/pro/search?keyWord=" + $("#kw").val(),
                    columns: [[
                        {field: "id", title: "编号", width: 100},
                        {field: "name", title: "名称", width: 100},
                        {field: "address", title: "地址", width: 100},
                        {
                            field: "url", title: "图片", width: 100, formatter: function (view, row, index) {
                                return "<img src='${pageContext.request.contextPath}/image/" + row.url + "' width='100'/>"
                            }
                        }
                    ]]
                });
            });
        });


    </script>
<body>
<div id="head_wrapper" class="s-isindex-wrap head_wrapper s-title-img ">
    <div id="s_fm" class="s_form">
        <div class="s_form_wrapper" id="s_form_wrapper">
            <div id="lg" class="s-p-top">
                <img id="s_lg_img" src="./index_files/logo.png">
            </div>
            <form id="form" class="fm">
                <input type="text" class="s_ipt" name="keyWord" id="kw" maxlength="100" autocomplete="off">
                <input type="button" value="搜索一下" id="su" class="btn self-btn bg s_btn">
            </form>

        </div>
    </div>
</div>


<hr>
<div id="ff"></div>

</body>
</html>