/**
 * Created by vegasjm on 01/08/2016.
 */
var VIDEORENTAL = VIDEORENTAL || {
        customersTableId : '#video-rental-customers-table',
        addCustomerDialogId: '#video-rental-add-customer-dialog',
        addCustomerDialog: null,

        customerTransactionsTableId: '#video-rental-customers-transactions-table',
        addCustomerTransactionDialogId: '#video-rental-add-customer-transaction-dialog',
        addCustomerTransactionDialog: null,

        selectedCustomerId:null,

        customerTable:null,
        customerTransactionsTable:null,
        init: function() {
            $(VIDEORENTAL.customersTableId).html('');
            VIDEORENTAL.buildTable();
            VIDEORENTAL.buildDialogs();
            VIDEORENTAL.buildEvents();
        },
        buildTable:function(){
            var customersData;
            $.ajax("/videorental/customers/getAllCustomers",{
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
                    {name:'ID',             objectMap:'id',             keyColumn:true},
                    {name:'Bonus',          objectMap:'bonus',          classStyle:'vc-table-col-small'},
                    {name:'Name',           objectMap:'firstName',      classStyle:'vc-table-col-big'},
                    {name:'Surname',        objectMap:'lastName',       classStyle:'vc-table-col-big'}
                ],
                buttons:[
                    {name:'Create',         domId:'create-button',      icon:'ui-icon-plus',                func:VIDEORENTAL.buttons.create}
                ]
            });
            $(VIDEORENTAL.customersTableId).html('');
            VIDEORENTAL.customerTable.render();

        },
        buildDialogs: function(){
            VIDEORENTAL.addCustomerDialog = $(VIDEORENTAL.addCustomerDialogId).dialog({
                autoOpen: false,
                height: 220,
                width: 350,
                modal: true,
                resizable: false,
                buttons: {
                    "Add": function(){
                        $.ajax("/videorental/customers/insertCustomer",{
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
                        VIDEORENTAL.addCustomerDialog.dialog( "close" );
                    },
                    "Cancel": function() {
                        VIDEORENTAL.addCustomerDialog.dialog( "close" );
                    }
                }
            });

            VIDEORENTAL.addCustomerTransactionDialog = $(VIDEORENTAL.addCustomerTransactionDialogId).dialog({
                autoOpen: false,
                height: 220,
                width: 350,
                modal: true,
                resizable: false,
                buttons: {
                    "Add Transaction": function(){
                        $.ajax("/videorental/management/insertCustomerTransaction",{
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
                        VIDEORENTAL.addCustomerTransactionDialog.dialog( "close" );
                    },
                    "Cancel": function() {
                        VIDEORENTAL.addCustomerTransactionDialog.dialog( "close" );
                    }
                }
            });
            $.ajax("/videorental/management/getAllMovies",{
                type: 'GET',
                dataType: 'json',
                async : false,
                cache: false,
                success: (function (data, textStatus, jqXHR) {
                    var movies = data;
                    $.each(movies,function(idx, movie){
                        $('#movieNewCustoTx').append('<option value="'+movie.id+'">'+movie.title+'</option>');
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
        buildEvents:function(){
          $('.video-rental-customers-table-row').click(
              function(){
                  var customerId = this.getAttribute('data-object-id');
                  VIDEORENTAL.selectedCustomerId = customerId;
                  VIDEORENTAL.buildTransactionsTable(VIDEORENTAL.selectedCustomerId);
              }
          );

            $('#restartDatabase').click(
                function(){
                    $.ajax("/videorental/initDataLoad",{
                        type: 'GET',
                        dataType: 'json',
                        async : false,
                        cache: false,
                        success: (function (data, textStatus, jqXHR) {
                            $(VIDEORENTAL.customerTransactionsTableId).html('');
                            $(VIDEORENTAL.customersTableId).html('');
                            VIDEORENTAL.init();
                            $.gritter.add({
                                title: 'Database Reset',
                                text: 'Database Reset succesfully.',
                                sticky: false,
                                class_name: 'gritter-success',
                                fade_in_speed: 'medium',
                                fade_out_speed: 2000,
                                time: 3000
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
        },
        buildTransactionsTable: function(customerId){
            $.ajax("/videorental/management/getCustomerTransactions?customerId="+customerId,{
                type: 'GET',
                dataType: 'json',
                async : false,
                cache: false,
                success: (function (data, textStatus, jqXHR) {
                    $(VIDEORENTAL.customerTransactionsTableId).html('');
                    var customerTransactions = data;
                    VIDEORENTAL.customerTransactionsTable = new TableGenerator({
                        name: VIDEORENTAL.customerTransactionsTableId,
                        data: customerTransactions,
                        description: 'Table with the customer transactions.',
                        tableContainerId: VIDEORENTAL.customerTransactionsTableId,
                        colDef: [
                            {name:'ID',              objectMap:'id',              keyColumn:true},
                            {name:'Customer',        objectMap:'customer',        classStyle:'vc-table-col-big'},
                            {name:'Movie',           objectMap:'movie',           classStyle:'vc-table-col-big-extra'},
                            {name:'Days',            objectMap:'nDays',           classStyle:'vc-table-col-small'},
                            {name:'Extra Days',      objectMap:'nExtraDays'},
                            {name:'Price',           objectMap:'price',           classStyle:'vc-table-col-small'},
                            {name:'Pr In time',      objectMap:'priceInTime'     },
                            {name:'Pr Extra',        objectMap:'priceInExtraTime'},
                            {name:'Paid',            objectMap:'isSettled'}
                        ],
                        buttons:[
                            {name:'Create',         domId:'create-button',      icon:'ui-icon-plus',                func:VIDEORENTAL.buttons.createTx}
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