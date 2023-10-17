<#include "base.ftl">

<#macro title>
    Закажи мастера
</#macro>

<script>
    $(document).on("click", "#submit", function () {
        if (!$("#master").val() || !$("#service").val()) {
            $("#status").text("Не выбран мастер и/или услуга")
        }
        else if (!$("#number").val()) {
            $("#status").text("Не введен номер телефона")
        }
        else if (!$("#date").val() || !$("#date").val()) {
            $("#status").text("Не выбрана дата")
        }
        else {
            $.get("/submit?master=" + $("#master").val() + "&service=" + $("#service").val() + "&number=" +
                $("#number").val() + "&date=" + $("#date").val() + "&time="+ $("#time").val(), function (response) {
                switch (response) {
                    case "ok":
                        $("#status").text("Вы записаны")
                        break
                    case "fail_collision":
                        $("#status").text("Мастер занят в это время, выберите другое время")
                        break
                    case "fail_not_working":
                        $("#status").text("Мастер не работает в это время")
                        break
                }
            })
        }
    })
</script>

<#macro content>
    Выберите мастера:
    <select id="master">
        <option disabled selected>Мастер</option>
        <#if masters?has_content>
            <#list masters as master>
                <option value="${master.id}">${master.name} ${master.lastname}</option>
            </#list>
        </#if>
    </select>
    <br>
    Выберите услугу:
    <select id="service" >
        <option disabled selected>Услуга</option>
        <#if services?has_content>
            <#list services as service>
                <option value="${service.id}">${service.name}</option>
            </#list>
        </#if>
    </select>
    <br>
    Выберите дату:
    <input type="date" id ="date">
    <br>
    Выберите время:
    <input type="time" id="time">
    <br>
    Оставьте номер телефона для контакта с Вами:
    <input type="text" id="number" name="number"/>
    <br>

    <input type="submit" id="submit" value="Заказать услугу">

    <br><br>
    <div id="status"><br></div>
    <br><br><br>
    <u>О нас:</u>
    <br>
    Email: contact@orgermaster.com
    <br>
    Telegram: @ordermaster
    <br>
    Адрес: г.Казань, ул.Кремлёвская, д.35
    <br>
    <iframe src="https://yandex.ru/map-widget/v1/?um=constructor%3A75feafff42b20ffe21718e984b24bac627097a29675c45a42a8631437cf2c2c9&amp;source=constructor" width="400" height="300" frameborder="0" align="left"></iframe>
</#macro>