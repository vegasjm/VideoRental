function TableGenerator(params){
    this.name = params.name;
    this.colDef = params.colDef;
    this.description = params.description;
    this.data = params.data;
    this.buttons = params.buttons;
    this.tableContainerId = params.tableContainerId;
    this.tableContainerElement;
    if(this.tableContainerId!=null) {
        if (this.tableContainerId.indexOf('#') == 0) {
            this.tableContainerElement = $(this.tableContainerId);
        } else {
            this.tableContainerElement = $('#' + this.tableContainerId);
        }
    }
    this.render = function() {
        //DESCRIPTION
        if (this.description != null && this.description != '') {
            var tableInfo = document.createElement('div');
            tableInfo.classList.add('vc-info');
            tableInfo.id = this.name+'-information';
            var iconInfo = document.createElement('span');
            iconInfo.classList.add('ui-icon');
            iconInfo.classList.add('ui-icon-info');
            iconInfo.classList.add('vc-icon-bigger');
            var textInfo = document.createElement('span');
            textInfo.classList.add('vc-info-text');
            textInfo.textContent = this.description;
            textInfo.style.fontSize = '13px';
            tableInfo.appendChild(iconInfo);
            tableInfo.appendChild(textInfo);
            this.tableContainerElement[0].appendChild(tableInfo);
        }
        var table = document.createElement('div');
        table.id = this.name;
        table.classList.add('vc-table');


        //HEADER
        var header = document.createElement('div');
        header.id= this.name+'-header';
        header.classList.add('vc-table-header');
        var headerCols = document.createElement('ul');
        $.each(this.colDef, function (idx, col) {
            var colItem = document.createElement('li');
            colItem.id = this.name+'-header'+'-'+col.objectMap;
            colItem.textContent = col.name;
            if(col.classStyle!=null){
                colItem.classList.add(col.classStyle);
            }
            headerCols.appendChild(colItem);
        });
        header.appendChild(headerCols);
        table.appendChild(header);


        //BODY
        var body = document.createElement('div');
        body.id= this.name+'-body';
        body.classList.add('vc-table-body');
        var colDefAux = this.colDef;

        for (i = 0; i < this.data.length; i++) {
            var row = this.data[i];
            var rowEl = document.createElement('ul');
            rowEl.classList.add(this.name.replace('#','')+'-row');
            for (w = 0; w < colDefAux.length; w++) {
                var col = colDefAux[w];
                var cellValue = row[col.objectMap];
                var cellItem = document.createElement('li');
                cellItem.id = this.name+'-cell'+'-'+col.objectMap;
                var cellValueEl = document.createElement('span');
                if(col.tooltip!=null && col.tooltip){
                    cellItem.classList.add('vc-image-url');
                    cellValueEl.classList.add('tooltip-info');
                    cellValueEl.classList.add('documents-table-url');
                    cellValueEl.title =
                        '<div>'+
                        '<div class="vc-tooltip-header">'+cellValue+'</div>'+
                        '<div><img style="max-width:200px;max-height:200px;" src="'+cellValue+'" /></div>'+
                        '</div>';
                }
                if(col.func==null) { //Default value
                    cellValueEl.textContent = cellValue;
                }else{ //There is specific function to format the data
                    cellValueEl.textContent = col.func(cellValue,row);
                }
                cellItem.appendChild(cellValueEl);
                if(col.classStyle!=null){cellItem.classList.add(col.classStyle);}
                if(col.keyColumn!=null && col.keyColumn){
                    cellItem.classList.add('vc-table-col-key');
                    rowEl.setAttribute('data-object-id',cellValue);
                }
                rowEl.appendChild(cellItem);
            }
            body.appendChild(rowEl);
        }

        table.appendChild(body);


        //FOOTER
        var footer = document.createElement('div');
        footer.id= this.name+'-footer';
        footer.classList.add('vc-table-footer');
        $.each(this.buttons, function (idx, button) {
            var buttonEl = document.createElement('button');
            buttonEl.classList.add('vc-table-tool-item');
            buttonEl.id = this.name+'-'+button.domId;
            var iconInfo = document.createElement('span');
            iconInfo.classList.add('ui-icon');
            iconInfo.classList.add(button.icon);
            iconInfo.classList.add('vc-icon-bigger');
            var textInfo = document.createElement('span');
            textInfo.classList.add('vc-table-tool-item-label');
            textInfo.textContent = button.name;
            textInfo.style.fontSize = '13px';
            buttonEl.appendChild(iconInfo);
            buttonEl.appendChild(textInfo);
            buttonEl.onclick = button.func;
            footer.appendChild(buttonEl);
        });
        table.appendChild(footer);

        this.tableContainerElement[0].appendChild(table);
    }
};

$(window).bind("load", function() {
    try { 				//JQuery 2.+ LIVE function equivalent
        $(document).on({
            click: function (e) {
                downloadURI(e.currentTarget.innerText,'Document');
            }
        }, ".vc-image-url");
    } catch (err) {       //JQuery 1.+ LIVE function
        $('.vc-image-url').live('click', function (e) {
            downloadURI(e.currentTarget.innerText,'Document');
        });
    }
});
function downloadURI(uri, name){
    var link = document.createElement("a");
    link.download = name;
    link.href = uri;
    link.click();
}
