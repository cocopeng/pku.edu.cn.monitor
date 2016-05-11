/**
 * Created by hongye.wei on 2014/12/8.
 */
 index = 0;
center_name_click = '';
$('.test > li').click(function () {
    var tabname = getTabTitle(this);
    navClick(tabname);
});

function addPanel() {
    var OnTags = $('span.tabs-title'), num = 0;
    var no = OnTags.text().indexOf("逻辑监控2");
    if (no < 0) {
        $('#tt').tabs('add', {
            title: '逻辑监控2 ',
            content: '<div style="padding:10px">Content' + index + '</div>',
            closable: true
        });
    }
    else {
        for (var ij = 0; ij < no; ij++) {
            if (OnTags.text().charAt(ij) == ' ')
                num++;
        }
        $('#tt').tabs("select", num);
    }

}
function removePanel() {
    var tab = $('#tt').tabs('getSelected');
    if (tab) {
        var index = $('#tt').tabs('getTabIndex', tab);
        $('#tt').tabs('close', index);
    }
}

function getTabTitle(e){
    return $(e).text();
}
function navClick(tabname,centerName){
    if(centerName == undefined){
        centerName = '';
    }
    center_name_click = centerName;

    var OnTags;
    OnTags = $('span.tabs-title');
    var ontabs = OnTags.text();
    var ontabsStr = ontabs.split(' ');

    var num = ontabsStr.indexOf(tabname);
    console.log('num',num);

    var logic = $('<div  style="padding:0.2px ;height:100%"><iframe name="logic" frameborder="0" src="new.html" height="100%" width="100%"  ></iframe></div>');
    var logictow = $('<div  style="padding:0.2px ;height:100%"><iframe name="logictow" frameborder="0" src="map.html" height="100%" width="100%"  ></iframe></div>');

    var logicdata = $('<div  style="padding:0.2px ;height:100%"><iframe name="logicdata" frameborder="0" src="http://60.5.1.6:8081/datamaintenance/datatables3.jsp" height="100%" width="100%"  ></iframe></div>');
    
    var selectTab;
    if (tabname == "逻辑视图") {
        selectTab = logic;
    }
    if (tabname == "物理视图"){
        selectTab = logictow;
    }
    if (tabname == "元数据列表"){
        selectTab = logicdata;
    }
    if (num < 0) {
        $('#tt').tabs('add', {
            title: tabname + ' ',
            content: selectTab,
            closable: true
        });
        onload[index] = 1;

    }
    else {
        index = 0;
        for (var ij = 0; ij < num; ij++) {
            if (ontabsStr[ij] == ' ') {
                index++;
            }
        }
        $('#tt').tabs("select", num);
        if(tabname == '逻辑视图'){
            parent.frames["logic"].window.centerLogicFlesh();
        }

    }


}