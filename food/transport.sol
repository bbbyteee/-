pragma solidity^0.4.25;

contract transport{
    uint256 m_id;                   //食品id
    string t_name;                  //单位名称
    string t_in_date;               //当前入库日期
    string t_out_date;              //当前出库日期
    string t_type_transportation;   //当前运输类型
    string t_destination;           //主要流向

    constructor(uint256 id,string name,string in_date,string out_date,string type_transportation,string destination)public{
        m_id = id;
        t_name = name;
        t_in_date = in_date;
        t_out_date = out_date;
        t_type_transportation = type_transportation;
        t_destination = destination;
    }

    function getTransportInfo()public view returns(uint256,string,string,string,string,string){
        return(m_id,t_name,t_in_date,t_out_date,t_type_transportation,t_destination);
    }    

}