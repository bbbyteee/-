pragma solidity^0.4.25;

contract manufacturer{
    
    uint256 m_id;               //食品id
    string m_name;              //食品名称
    string m_origin_place;      //食品产地
    string m_date_manufacture;  //当前生产日期
    string m_in_date;           //当前入库日期
    string m_packaging_date;    //当前打包日期
    string m_out_date;          //当前出库日期

    constructor(uint256 id,string name,string origin_place,
    string date_manufacture,string in_date,string packaging_date,string out_date)public{
        m_id = id;
        m_name = name;
        m_origin_place = origin_place;
        m_date_manufacture = date_manufacture;
        m_in_date = in_date;
        m_packaging_date = packaging_date;
        m_out_date = out_date;
    }

    function getManufacturerInfo()public view returns(uint256,
    string,string,string,string,string,string){
        return(m_id,m_name,m_origin_place,m_date_manufacture,
        m_in_date,m_packaging_date,m_out_date);
    }

}