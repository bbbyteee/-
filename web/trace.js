<!--该脚本用于实现--连接区块链及运行智能合约-->

// 页面加载时获取Web3对象
flag = false;
window.addEventListener('load',function(){
    //检查web3是否已经注入到(Mist/MetaMask)
    // if(typeof web3 !== 'undefined'){
    //     //使用 Mist/MetaMask 的提供者
    //     web3js = new Web3(web3.currentProvider);
    // }else{
    // 处理用户没安装的情况，比如显示一个消息
    // 告诉他们要安装MetaMask 来使用我们的应用
    web3js = new Web3(new Web3.providers
        .HttpProvider("HTTP://127.0.0.1:7545"));

    // }

    // 现在你可以启动你的应用并自由访问web3.js：
    startApp();

})
var traceContract;
function startApp(){
    var abi = [
        {
            "constant": false,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                },
                {
                    "name": "name",
                    "type": "string"
                },
                {
                    "name": "origin_place",
                    "type": "string"
                },
                {
                    "name": "date_manufacture",
                    "type": "string"
                },
                {
                    "name": "in_date",
                    "type": "string"
                },
                {
                    "name": "packaging_date",
                    "type": "string"
                },
                {
                    "name": "out_date",
                    "type": "string"
                }
            ],
            "name": "addFoodByManufacturer",
            "outputs": [
                {
                    "name": "",
                    "type": "bool"
                }
            ],
            "payable": false,
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "constant": false,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                },
                {
                    "name": "_seller",
                    "type": "string"
                },
                {
                    "name": "COVID19_Report",
                    "type": "string"
                },
                {
                    "name": "in_date",
                    "type": "string"
                },
                {
                    "name": "out_date",
                    "type": "string"
                },
                {
                    "name": "launch_date",
                    "type": "string"
                }
            ],
            "name": "addFoodBySeller",
            "outputs": [
                {
                    "name": "",
                    "type": "bool"
                }
            ],
            "payable": false,
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "constant": false,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                },
                {
                    "name": "name",
                    "type": "string"
                },
                {
                    "name": "in_date",
                    "type": "string"
                },
                {
                    "name": "out_date",
                    "type": "string"
                },
                {
                    "name": "type_transportation",
                    "type": "string"
                },
                {
                    "name": "destination",
                    "type": "string"
                }
            ],
            "name": "addFoodByTransport",
            "outputs": [
                {
                    "name": "",
                    "type": "bool"
                }
            ],
            "payable": false,
            "stateMutability": "nonpayable",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                },
                {
                    "name": "name",
                    "type": "string"
                },
                {
                    "name": "origin_place",
                    "type": "string"
                },
                {
                    "name": "date_manufacture",
                    "type": "string"
                },
                {
                    "name": "in_date",
                    "type": "string"
                },
                {
                    "name": "packaging_date",
                    "type": "string"
                },
                {
                    "name": "out_date",
                    "type": "string"
                }
            ],
            "name": "checkHashByManufacturer",
            "outputs": [
                {
                    "name": "",
                    "type": "bool"
                },
                {
                    "name": "",
                    "type": "bytes32"
                },
                {
                    "name": "",
                    "type": "bytes32"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                },
                {
                    "name": "_seller",
                    "type": "string"
                },
                {
                    "name": "COVID19_Report",
                    "type": "string"
                },
                {
                    "name": "in_date",
                    "type": "string"
                },
                {
                    "name": "out_date",
                    "type": "string"
                },
                {
                    "name": "launch_date",
                    "type": "string"
                }
            ],
            "name": "checkHashBySeller",
            "outputs": [
                {
                    "name": "",
                    "type": "bool"
                },
                {
                    "name": "",
                    "type": "bytes32"
                },
                {
                    "name": "",
                    "type": "bytes32"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                },
                {
                    "name": "name",
                    "type": "string"
                },
                {
                    "name": "in_date",
                    "type": "string"
                },
                {
                    "name": "out_date",
                    "type": "string"
                },
                {
                    "name": "type_transportation",
                    "type": "string"
                },
                {
                    "name": "destination",
                    "type": "string"
                }
            ],
            "name": "checkHashByTransport",
            "outputs": [
                {
                    "name": "",
                    "type": "bool"
                },
                {
                    "name": "",
                    "type": "bytes32"
                },
                {
                    "name": "",
                    "type": "bytes32"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                }
            ],
            "name": "getFoodInfoByManufacturer",
            "outputs": [
                {
                    "name": "",
                    "type": "uint256"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                }
            ],
            "name": "getFoodInfoBySeller",
            "outputs": [
                {
                    "name": "",
                    "type": "uint256"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "id",
                    "type": "uint256"
                }
            ],
            "name": "getFoodInfoByTransport",
            "outputs": [
                {
                    "name": "",
                    "type": "uint256"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                },
                {
                    "name": "",
                    "type": "string"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "",
                    "type": "uint256"
                }
            ],
            "name": "manufacturerHash",
            "outputs": [
                {
                    "name": "",
                    "type": "bytes32"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "",
                    "type": "uint256"
                }
            ],
            "name": "manufacturerList",
            "outputs": [
                {
                    "name": "",
                    "type": "address"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "",
                    "type": "uint256"
                }
            ],
            "name": "sellerHash",
            "outputs": [
                {
                    "name": "",
                    "type": "bytes32"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "",
                    "type": "uint256"
                }
            ],
            "name": "sellerList",
            "outputs": [
                {
                    "name": "",
                    "type": "address"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "",
                    "type": "uint256"
                }
            ],
            "name": "transportHash",
            "outputs": [
                {
                    "name": "",
                    "type": "bytes32"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        },
        {
            "constant": true,
            "inputs": [
                {
                    "name": "",
                    "type": "uint256"
                }
            ],
            "name": "transportList",
            "outputs": [
                {
                    "name": "",
                    "type": "address"
                }
            ],
            "payable": false,
            "stateMutability": "view",
            "type": "function"
        }
    ]
    var contractAddress = "0x092057E16b555c1724fb92a94ECfCB4F8B6EbaE3";
    traceContract = new web3js.eth.Contract(abi,contractAddress);
}

function InsertManufacturer(){

    let data = $('#ManufacturerInsertForm').serializeArray();

    $.get('/WebDemo/manufacturerQuery_id',data, function (result) {
        //查询成功的结果
        const id = web3js.utils.toBN(result.m_id);

        try{
            web3js.eth.getAccounts(function(error,accounts){
                traceContract.methods.addFoodByManufacturer(id.toString(),data[4].value,data[5].value,
                    data[6].value,data[7].value,data[8].value,data[9].value).send({
                    from:accounts[0],
                    gas:6000000
                }).on('receipt', (data) => {
                    console.log(data)
                    alert("transactionHash: " + data.transactionHash+"\n"+
                    "blockHash: " + data.blockHash+"\n"+
                    "from: " + data.from+"\n"+
                    "to: " + data.to);
                })
            });
        }catch(error){

        }
    });
}

function InsertTransport(){

    let data = $('#TransportInsertForm').serializeArray();

    const id = web3js.utils.toBN(data[1].value);

    try{
        web3js.eth.getAccounts(function(error,accounts){
            traceContract.methods.addFoodByTransport(id.toString(),data[2].value,
                data[6].value,data[7].value,data[8].value,data[9].value).send({
                from:accounts[0],
                gas:6000000
            }).on('receipt', (data) => {
                console.log(data);
                alert("transactionHash: " + data.transactionHash+"\n"+
                    "blockHash: " + data.blockHash+"\n"+
                    "from: " + data.from+"\n"+
                    "to: " + data.to);
            })
        });
    }catch(error){

    }
}

function InsertSeller(){

    let data = $('#SellerInsertForm').serializeArray();

    const id = web3js.utils.toBN(data[1].value);

    try{
        web3js.eth.getAccounts(function(error,accounts){
            traceContract.methods.addFoodBySeller(id.toString(),data[2].value,
                data[3].value,data[4].value,data[5].value,data[6].value).send({
                from:accounts[0],
                gas:6000000
            }).on('receipt', (data) => {
                console.log(data);
                alert("transactionHash: " + data.transactionHash+"\n"+
                    "blockHash: " + data.blockHash+"\n"+
                    "from: " + data.from+"\n"+
                    "to: " + data.to);
            })
        });
    }catch(error){

    }
}

function checkHashByManufacturer(btn){
    let tr = btn.parentNode.parentNode;//btn-->td-->tr
    //获取到多个数据
    let m_id = $(tr.childNodes[0]).text();
    let m_foodname = $(tr.childNodes[1]).text();
    let m_origin_place = $(tr.childNodes[2]).text();
    let m_date_manufacture = $(tr.childNodes[3]).text();
    let m_in_date = $(tr.childNodes[4]).text();
    let m_packaging_date = $(tr.childNodes[5]).text();
    let m_out_date = $(tr.childNodes[6]).text();
    const id = web3js.utils.toBN(m_id);

    try{
        web3js.eth.getAccounts(function(error,accounts){
            traceContract.methods.checkHashByManufacturer(id,m_foodname,m_origin_place,m_date_manufacture,
                m_in_date,m_packaging_date,m_out_date).call((err,result)=>{

                    if(result[0] == true){
                        alert("当前信息hash值为："+result[2]+"\n"+
                            "上链信息hash值为"+result[1]+"\n"+
                            "生产商信息未被篡改！")
                    }else {
                        alert("当前信息hash值为："+result[2]+"\n"+
                            "上链信息hash值为"+result[1]+"\n"+
                            "生产商信息已被修改！")
                        }
            });
        });
    }catch(error){

    }
}

function checkHashByTransport(btn){
    let tr = btn.parentNode.parentNode;//btn-->td-->tr
    //获取到多个数据
    let m_id = $(tr.childNodes[0]).text();
    let t_name = $(tr.childNodes[1]).text();
    let t_in_date = $(tr.childNodes[2]).text();
    let t_out_date = $(tr.childNodes[3]).text();
    let t_type_transportation = $(tr.childNodes[4]).text();
    let t_destination = $(tr.childNodes[5]).text();
    const id = web3js.utils.toBN(m_id);

    try{
        web3js.eth.getAccounts(function(error,accounts){
            traceContract.methods.checkHashByTransport(id,t_name,t_in_date,t_out_date,
                t_type_transportation,t_destination).call((err,result)=>{

                if(result[0]==true){
                    alert("当前信息hash值为："+result[2]+"\n"+
                        "上链信息hash值为"+result[1]+"\n"+
                        "运输商信息未被篡改！")
                }
                else {
                    alert("当前信息hash值为："+result[2]+"\n"+
                        "上链信息hash值为"+result[1]+"\n"+
                        "运输商信息已被修改！")
                }
            });
        });
    }catch(error){

    }
}

function checkHashBySeller(btn){
    let tr = btn.parentNode.parentNode;//btn-->td-->tr
    //获取到多个数据
    let m_id = $(tr.childNodes[0]).text();
    let t_keeper_storage = $(tr.childNodes[1]).text();
    let t_COVID = $(tr.childNodes[2]).text();
    let t_in_date = $(tr.childNodes[3]).text();
    let t_out_date = $(tr.childNodes[4]).text();
    let t_launch_date = $(tr.childNodes[5]).text();
    const id = web3js.utils.toBN(m_id);

    try{
        web3js.eth.getAccounts(function(error,accounts){
            traceContract.methods.checkHashBySeller(id,t_keeper_storage,t_COVID,
                t_in_date,t_out_date,t_launch_date).call((err,result)=>{

                if(result[0]==true){
                    alert("当前信息hash值为："+result[2]+"\n"+
                        "上链信息hash值为"+result[1]+"\n"+
                        "销售商信息未被篡改！")
                }else {
                    alert("当前信息hash值为："+result[2]+"\n"+
                        "上链信息hash值为"+result[1]+"\n"+
                        "销售商信息已被修改！")
                }
            });
        });
    }catch(error){

    }
}