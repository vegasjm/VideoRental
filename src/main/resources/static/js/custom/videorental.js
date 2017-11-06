/**
 * Created by vegasjm on 05/11/2017.
 */
var VIDEORENTAL = VIDEORENTAL || {
        customersTableId : '#video-rental-customers-table',
        addCustomerDialogId: '#video-rental-add-customer-dialog',
        addCustomerDialog: null,

        customerTransactionsTableId: '#video-rental-customers-transactions-table',
        addCustomerTransactionDialogId: '#video-rental-add-customer-transaction-dialog',
        addCustomerTransactionDialog: null,

        priceSimulationDialogId: '#video-rental-price-simulation-dialog',
        priceSimulationDialog: null,

        selectedCustomerId:null,

        customerTable:null,
        customerTransactionsTable:null,
        init: function() {
            $(VIDEORENTAL.customersTableId).html('');
            VIDEORENTAL.buildTable();
            VIDEORENTAL.buildDialogs();
            VIDEORENTAL.buildEvents();
            $.ajax("api/management/getAllMovies",{
                type: 'GET',
                dataType: 'json',
                async : false,
                cache: false,
                success: (function (data, textStatus, jqXHR) {
                    var movies = data;
                    $.each(movies,function(idx, movie){
                        $('#movieNewCustoTx').append('<option value="'+movie.id+'">'+movie.title+'</option>');
                        $('#movieSimulate').append('<option value="'+movie.id+'">'+movie.title+'</option>');
                    });
                }),
                error: (function (jqXHR, textStatus, errorThrown) {
                    $.gritter.add({
                        title: 'ERROR while loading movies.',
                        text: 'Check the database connection.',
                        sticky: false,
                        class_name: 'gritter-error',
                        fade_in_speed: 'medium',
                        fade_out_speed: 2000,
                        time: 3000
                    });
                }),
                showErrorToast: false
            });
        },
        buildTable:function(){
            var customersData;
            $.ajax("api/customers/getAllCustomers",{
                type: 'GET',
                dataType: 'json',
                async : false,
                cache: false,
                success: (function (data, textStatus, jqXHR) {
                    customersData = data;
                    $.gritter.add({
                        title: 'Customers table succesfully Load',
                        text: 'Customers table succesfully load',
                        sticky: false,
                        class_name: 'gritter-success',
                        fade_in_speed: 'medium',
                        fade_out_speed: 2000,
                        time: 3000
                    });
                }),
                error: (function (jqXHR, textStatus, errorThrown) {
                    $.gritter.add({
                        title: 'ERROR while loading Customers table.',
                        text: 'Check the database connection.',
                        sticky: false,
                        class_name: 'gritter-error',
                        fade_in_speed: 'medium',
                        fade_out_speed: 2000,
                        time: 3000
                    });
                }),
                showErrorToast: false
            });

            VIDEORENTAL.customerTable = new TableGenerator({
                name: VIDEORENTAL.customersTableId,
                data: customersData,
                description: 'Click on one Customer to see the related transactions.',
                tableContainerId: VIDEORENTAL.customersTableId,
                colDef: [
                    {name:'ID',             objectMap:'id',             keyColumn:true, classStyle:'vc-table-col-small'},
                    {name:'Bonus',          objectMap:'bonus',          classStyle:'vc-table-col-small'},
                    {name:'Name',           objectMap:'firstName'},
                    {name:'Surname',        objectMap:'lastName'}
                ],
                buttons:[
                    {name:'Create',         domId:'create-button',      icon:'ui-icon-plus',                func:VIDEORENTAL.buttons.create}
                ]
            });
            $(VIDEORENTAL.customersTableId).html('');
            if(customersData!=null)
            VIDEORENTAL.customerTable.render();
            $('.video-rental-customers-table-row').click(
                function(){
                    var customerId = this.getAttribute('data-object-id');
                    VIDEORENTAL.selectedCustomerId = customerId;
                    VIDEORENTAL.buildTransactionsTable(VIDEORENTAL.selectedCustomerId);
                }
            );
            },
        closeFunction:function(){
            VIDEORENTAL.priceSimulationDialog.dialog('destroy');
            VIDEORENTAL.addCustomerTransactionDialog.dialog('destroy');
            VIDEORENTAL.addCustomerDialog.dialog('destroy');
            VIDEORENTAL.buildDialogs();
        },
        buildDialogs: function(){
            VIDEORENTAL.addCustomerDialog = $(VIDEORENTAL.addCustomerDialogId).dialog({
                autoOpen: false,
                height: 240,
                width: 300,
                modal: true,
                resizable: false,
                buttons: {
                    "Add": function(){
                        $.ajax("api/customers/insertCustomer",{
                            type: 'POST',
                            data: {
                                name: $("#nameNewCusto").val(),
                                surname: $("#surnameNewCusto").val()
                            },
                            dataType: 'json',
                            cache: false,
                            async: false,
                            showErrorToast: false,
                            success: (function (data, textStatus, jqXHR) {
                                if(data) {
                                    $.gritter.add({
                                        title: 'Customer succesfully added',
                                        text: 'Customer succesfully added',
                                        sticky: false,
                                        class_name: 'gritter-success',
                                        fade_in_speed: 'medium',
                                        fade_out_speed: 2000,
                                        time: 3000
                                    });
                                    VIDEORENTAL.buildTable();
                                }else{
                                    $.gritter.add({
                                        title: 'ERROR while adding Customer.',
                                        text: 'Check the database connection.',
                                        sticky: false,
                                        class_name: 'gritter-error',
                                        fade_in_speed: 'medium',
                                        fade_out_speed: 2000,
                                        time: 3000
                                    });
                                }
                            }),
                            error: (function (jqXHR, textStatus, errorThrown) {
                                $.gritter.add({
                                    title: 'ERROR while adding Customer.',
                                    text: 'Check the database connection.',
                                    sticky: false,
                                    class_name: 'gritter-error',
                                    fade_in_speed: 'medium',
                                    fade_out_speed: 2000,
                                    time: 3000
                                });
                            })
                        });
                        VIDEORENTAL.closeFunction();
                    },
                    "Cancel": function() {
                        VIDEORENTAL.closeFunction();
                    }
                }
            });

            VIDEORENTAL.addCustomerTransactionDialog = $(VIDEORENTAL.addCustomerTransactionDialogId).dialog({
                autoOpen: false,
                height: 300,
                width: 300,
                modal: true,
                resizable: false,
                buttons: {
                    "Add Transaction": function(){
                        $.ajax("api/management/insertCustomerTransaction",{
                            type: 'POST',
                            data: {
                                customerId: VIDEORENTAL.selectedCustomerId,
                                movieId: $("#movieNewCustoTx").val(),
                                nDays: $("#nDaysNewCustoTx").val(),
                                nExtraDays: $("#nExtraDaysNewCustoTx").val()
                            },
                            dataType: 'json',
                            cache: false,
                            async: false,
                            showErrorToast: false,
                            success: (function (data, textStatus, jqXHR) {
                                if(data) {
                                    $.gritter.add({
                                        title: 'Customer Transaction succesfully added',
                                        text: 'Customer Transaction succesfully added',
                                        sticky: false,
                                        class_name: 'gritter-success',
                                        fade_in_speed: 'medium',
                                        fade_out_speed: 2000,
                                        time: 3000
                                    });
                                    VIDEORENTAL.buildTable();
                                }else{
                                    $.gritter.add({
                                        title: 'ERROR while adding Customer.',
                                        text: 'Check the database connection.',
                                        sticky: false,
                                        class_name: 'gritter-error',
                                        fade_in_speed: 'medium',
                                        fade_out_speed: 2000,
                                        time: 3000
                                    });
                                }
                            }),
                            error: (function (jqXHR, textStatus, errorThrown) {
                                $.gritter.add({
                                    title: 'ERROR while adding Customer Transaction.',
                                    text: 'Check the database connection.',
                                    sticky: false,
                                    class_name: 'gritter-error',
                                    fade_in_speed: 'medium',
                                    fade_out_speed: 2000,
                                    time: 3000
                                });
                            })
                        });
                        $(VIDEORENTAL.customerTransactionsTableId).html('');
                        VIDEORENTAL.buildTransactionsTable(VIDEORENTAL.selectedCustomerId);
                        $('.video-rental-customers-table-row').click(
                            function(){
                                var customerId = this.getAttribute('data-object-id');
                                VIDEORENTAL.selectedCustomerId = customerId;
                                VIDEORENTAL.buildTransactionsTable(VIDEORENTAL.selectedCustomerId);
                            }
                        );
                        VIDEORENTAL.closeFunction();
                    },
                    "Cancel": function() {
                        VIDEORENTAL.closeFunction();
                    }
                }
            });

            VIDEORENTAL.priceSimulationDialog = $(VIDEORENTAL.priceSimulationDialogId).dialog({
                autoOpen: false,
                height: 300,
                width: 300,
                modal: true,
                resizable: false,
                buttons: {
                    "Simulate Price": function(){
                        $.ajax("api/management/priceSimulation",{
                            type: 'POST',
                            data: {
                                movieId: $("#movieSimulate").val(),
                                nDays: $("#nDaysSimulate").val(),
                                nExtraDays: $("#nExtraDaysSimulate").val()
                            },
                            dataType: 'json',
                            cache: false,
                            async: false,
                            showErrorToast: false,
                            success: (function (data, textStatus, jqXHR) {
                                $('#priceValue').html('<span style="color:green; font-weight: bolder;">PRICE is:'+data+' SEK</span>');
                            }),
                            error: (function (jqXHR, textStatus, errorThrown) {
                                $.gritter.add({
                                    title: 'ERROR while Simulating Price.',
                                    text: 'Check the database connection.',
                                    sticky: false,
                                    class_name: 'gritter-error',
                                    fade_in_speed: 'medium',
                                    fade_out_speed: 2000,
                                    time: 3000
                                });
                            })
                        });
                    },
                    "Cancel": function() {
                        VIDEORENTAL.closeFunction();
                    }
                }
            });
        },
        buildEvents:function(){
            $('#restartDatabase').click(
                function(){
                    $.ajax("/initDataLoad",{
                        type: 'GET',
                        dataType: 'json',
                        async : false,
                        cache: false,
                        success: (function (data, textStatus, jqXHR) {
                            $(VIDEORENTAL.customerTransactionsTableId).html(
                                '<br/><br/><br/><br/><br/><br/><br/><br/>'+
                                '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;'+
                                'Click in a Customer to see the associated transactions'
                            );
                            $(VIDEORENTAL.customersTableId).html('');
                            VIDEORENTAL.buildTable();
                            $.gritter.add({
                                title: 'Database Reset',
                                text: 'Database Reset succesfully.',
                                sticky: false,
                                class_name: 'gritter-success',
                                fade_in_speed: 'medium',
                                fade_out_speed: 2000,
                                time: 3000
                            });
                            if($('#movieNewCustoTx').html()==null || $('#movieNewCustoTx').html()=='')
                            $.ajax("api/management/getAllMovies",{
                                type: 'GET',
                                dataType: 'json',
                                async : false,
                                cache: false,
                                success: (function (data, textStatus, jqXHR) {
                                    var movies = data;
                                    $.each(movies,function(idx, movie){
                                        $('#movieNewCustoTx').append('<option value="'+movie.id+'">'+movie.title+'</option>');
                                        $('#movieSimulate').append('<option value="'+movie.id+'">'+movie.title+'</option>');
                                    });
                                }),
                                error: (function (jqXHR, textStatus, errorThrown) {
                                    $.gritter.add({
                                        title: 'ERROR while loading movies.',
                                        text: 'Check the database connection.',
                                        sticky: false,
                                        class_name: 'gritter-error',
                                        fade_in_speed: 'medium',
                                        fade_out_speed: 2000,
                                        time: 3000
                                    });
                                }),
                                showErrorToast: false
                            });
                        }),
                        error: (function (jqXHR, textStatus, errorThrown) {
                            $.gritter.add({
                                title: 'ERROR while Database Reset.',
                                text: 'Database Reset failed.',
                                sticky: false,
                                class_name: 'gritter-error',
                                fade_in_speed: 'medium',
                                fade_out_speed: 2000,
                                time: 3000
                            });
                        }),
                        showErrorToast: false
                    });
                }
            );
            $('#simulatePriceButton').click(function(){
                VIDEORENTAL.priceSimulationDialog.dialog( "open" );
            });
        },
        buildTransactionsTable: function(customerId){
            $.ajax("api/management/getCustomerTransactions?customerId="+customerId,{
                type: 'GET',
                dataType: 'json',
                async : false,
                cache: false,
                success: (function (data, textStatus, jqXHR) {
                    $(VIDEORENTAL.customerTransactionsTableId).html('');
                    var customerTransactions = data;
                    var customerName = '';
                    $.ajax("api/customers/getCustomer?id="+customerId,{
                        type: 'GET',
                        dataType: 'json',
                        async : false,
                        cache: false,
                        success: (function (data, textStatus, jqXHR) {
                            customerName = data.firstName+' '+data.lastName;
                        }),
                        showErrorToast: false
                    });
                    VIDEORENTAL.customerTransactionsTable = new TableGenerator({
                        name: VIDEORENTAL.customerTransactionsTableId,
                        data: customerTransactions,
                        description: 'Table with the transactions for the Customer: '+customerName,
                        tableContainerId: VIDEORENTAL.customerTransactionsTableId,
                        colDef: [
                            {name:'ID',              objectMap:'id',              keyColumn:true,   classStyle:'vc-table-col-small'},
                            {name:'Movie',           objectMap:'movie',           classStyle:'vc-table-col-big-extra'},
                            {name:'Days',            objectMap:'nDays',           classStyle:'vc-table-col-small'},
                            {name:'Extra Days',      objectMap:'nExtraDays'      },
                            {name:'Price',           objectMap:'price',           classStyle:'vc-table-col-small'},
                            {name:'Pr In time',      objectMap:'priceInTime'     },
                            {name:'Pr Extra',        objectMap:'priceInExtraTime'}
                        ],
                        buttons:[
                            {name:'Create',         domId:'createTx-button',      icon:'ui-icon-plus',                func:VIDEORENTAL.buttons.createTx}
                        ]
                    });
                    VIDEORENTAL.customerTransactionsTable.render();
                    $.gritter.add({
                        title: 'Customers Transactions table succesfully Load',
                        text: 'Customers Transactions table succesfully load',
                        sticky: false,
                        class_name: 'gritter-success',
                        fade_in_speed: 'medium',
                        fade_out_speed: 2000,
                        time: 3000
                    });
                }),
                error: (function (jqXHR, textStatus, errorThrown) {
                    $.gritter.add({
                        title: 'ERROR Transactions while loading Customers table.',
                        text: 'Check Transactions the database connection.',
                        sticky: false,
                        class_name: 'gritter-error',
                        fade_in_speed: 'medium',
                        fade_out_speed: 2000,
                        time: 3000
                    });
                }),
                showErrorToast: false
            });
        },
        buttons:{
            create:(function(){
                VIDEORENTAL.addCustomerDialog.dialog( "open" );
            }),
            createTx:(function(){
                VIDEORENTAL.addCustomerTransactionDialog.dialog( "open" );
            })
        }
    };

$(document).ready( function() {
        VIDEORENTAL.init();
    }
);