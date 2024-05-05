pragma solidity ^0.4.24;
import "./manufacturer.sol";
import "./transport.sol";
import "./seller.sol";

contract Trace {
    mapping(uint256 => address) public manufacturerList; //溯源码映射食品合约地址
    mapping(uint256 => address) public transportList; //溯源码映射食品合约地址
    mapping(uint256 => address) public sellerList; //溯源码映射食品合约地址
    mapping(uint256 => bytes32) public manufacturerHash;
    mapping(uint256 => bytes32) public transportHash;
    mapping(uint256 => bytes32) public sellerHash;

    //添加生产商
    function addFoodByManufacturer(
        uint256 id,
        string name,
        string origin_place,
        string date_manufacture,
        string in_date,
        string packaging_date,
        string out_date
    ) public returns (bool) {
        manufacturer m = new manufacturer(
            id,
            name,
            origin_place,
            date_manufacture,
            in_date,
            packaging_date,
            out_date
        );
        manufacturerList[id] = m;
        bytes32 hashValue = keccak256(
            abi.encode(
                id,
                name,
                origin_place,
                date_manufacture,
                in_date,
                packaging_date,
                out_date
            )
        );
        manufacturerHash[id] = hashValue;
        return true;
    }

    //添加运输商
    function addFoodByTransport(
        uint256 id,
        string name,
        string in_date,
        string out_date,
        string type_transportation,
        string destination
    ) public returns (bool) {
        transport t = new transport(
            id,
            name,
            in_date,
            out_date,
            type_transportation,
            destination
        );
        transportList[id] = t;
        bytes32 hashValue = keccak256(
            abi.encode(
                id,
                name,
                in_date,
                out_date,
                type_transportation,
                destination
            )
        );
        transportHash[id] = hashValue;
        return true;
    }

    //添加销售商
    function addFoodBySeller(
        uint256 id,
        string _seller,
        string COVID19_Report,
        string in_date,
        string out_date,
        string launch_date
    ) public returns (bool) {
        seller s = new seller(
            id,
            _seller,
            COVID19_Report,
            in_date,
            out_date,
            launch_date
        );
        sellerList[id] = s;
        bytes32 hashValue = keccak256(
            abi.encode(
                id,
                _seller,
                COVID19_Report,
                in_date,
                out_date,
                launch_date
            )
        );
        sellerHash[id] = hashValue;
        return true;
    }

    //生产商溯源
    function getFoodInfoByManufacturer(uint256 id)
        public
        view
        returns (
            uint256,
            string,
            string,
            string,
            string,
            string,
            string
        )
    {
        return manufacturer(manufacturerList[id]).getManufacturerInfo();
    }

    //运输商溯源
    function getFoodInfoByTransport(uint256 id)
        public
        view
        returns (
            uint256,
            string,
            string,
            string,
            string,
            string
        )
    {
        return transport(transportList[id]).getTransportInfo();
    }

    //销售商溯源
    function getFoodInfoBySeller(uint256 id)
        public
        view
        returns (
            uint256,
            string,
            string,
            string,
            string,
            string
        )
    {
        return seller(sellerList[id]).getSellerInfo();
    }

    //生产商hash验证
    function checkHashByManufacturer(
        uint256 id,
        string name,
        string origin_place,
        string date_manufacture,
        string in_date,
        string packaging_date,
        string out_date
    )
        public
        view
        returns (
            bool,
            bytes32,
            bytes32
        )
    {
        bytes32 oldHashValue = manufacturerHash[id];
        bytes32 newHashValue = keccak256(
            abi.encode(
                id,
                name,
                origin_place,
                date_manufacture,
                in_date,
                packaging_date,
                out_date
            )
        );
        if (oldHashValue != newHashValue)
            return (false, oldHashValue, newHashValue);
        return (true, oldHashValue, newHashValue);
    }

    //运输商hash验证
    function checkHashByTransport(
        uint256 id,
        string name,
        string in_date,
        string out_date,
        string type_transportation,
        string destination
    )
        public
        view
        returns (
            bool,
            bytes32,
            bytes32
        )
    {
        bytes32 oldHashValue = transportHash[id];
        bytes32 newHashValue = keccak256(
            abi.encode(
                id,
                name,
                in_date,
                out_date,
                type_transportation,
                destination
            )
        );
        if (oldHashValue != newHashValue)
            return (false, oldHashValue, newHashValue);
        return (true, oldHashValue, newHashValue);
    }

    //销售商hash验证
    function checkHashBySeller(
        uint256 id,
        string _seller,
        string COVID19_Report,
        string in_date,
        string out_date,
        string launch_date
    )
        public
        view
        returns (
            bool,
            bytes32,
            bytes32
        )
    {
        bytes32 oldHashValue = sellerHash[id];
        bytes32 newHashValue = keccak256(
            abi.encode(
                id,
                _seller,
                COVID19_Report,
                in_date,
                out_date,
                launch_date
            )
        );
        if (oldHashValue != newHashValue)
            return (false, oldHashValue, newHashValue);
        return (true, oldHashValue, newHashValue);
    }
}
