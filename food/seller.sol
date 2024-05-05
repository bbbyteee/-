pragma solidity^0.4.25;

contract seller{
    uint256 m_id;//食品id
    string s_seller;//仓库管理员
    string s_COVID19_Report;//新冠检测  阴性 阳性 未知
    string s_in_date;//入库日期
    string s_out_date;//出库日期
    string s_launch_date;//上架日期

    constructor(uint256 id,string _seller,string COVID19_Report,string in_date,string out_date,string launch_date)public{
        m_id = id;
        s_seller = _seller;
        s_COVID19_Report = COVID19_Report;
        s_in_date = in_date;
        s_out_date = out_date;
        s_launch_date = launch_date;
    }

    function getSellerInfo()public view returns(uint256,string,string,string,string,string){
        return(m_id,s_seller,s_COVID19_Report,s_in_date,s_out_date,s_launch_date);
    }
}