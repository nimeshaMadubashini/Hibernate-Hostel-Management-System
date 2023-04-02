package lk.ijse.hibernate.bo.custome.impl;

import lk.ijse.hibernate.bo.custome.RoomBO;
import lk.ijse.hibernate.dao.DAOFactory;
import lk.ijse.hibernate.dao.custome.RoomDAO;
import lk.ijse.hibernate.dto.RoomDTO;
import lk.ijse.hibernate.entity.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomBOImpl implements RoomBO {
    RoomDAO roomDAO= (RoomDAO) DAOFactory.getBoFactory().getDo(DAOFactory.DOType.ROOM);
    @Override
    public boolean save(RoomDTO dto) throws Exception {
        return roomDAO.save(new Room(dto.getRoom_type_id(), dto.getType(), dto.getKey_money(), dto.getQty())) ;
    }

    @Override
    public boolean update(RoomDTO dto) throws Exception {
        return roomDAO.update(new Room(dto.getRoom_type_id(), dto.getType(), dto.getKey_money(), dto.getQty())) ;

    }

    @Override
    public boolean delete(String id) throws Exception {
        return roomDAO.delete(id);
    }

    @Override
    public List<RoomDTO> loadAllRoomTOTable() throws Exception {
        List<RoomDTO> roomDTOList=new ArrayList<>();
        List<Room> list=roomDAO.loadAll();
        for (Room r:list) {
           roomDTOList.add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));
        }
        return roomDTOList;
    }

    @Override
    public List<String> loadRoomId() throws Exception {
        List<String> list=roomDAO.loadId();
        return  list;
    }

    @Override
    public List<RoomDTO> findRoomCM(String s) throws Exception {
        List<RoomDTO> roomDTOList=new ArrayList<>();
        List<Room> list=roomDAO.loadRoom(s);
        for (Room r:list) {
            roomDTOList.add(new RoomDTO(r.getRoom_type_id(),r.getType(),r.getKey_money(),r.getQty()));
        }
        return roomDTOList;
    }
}
