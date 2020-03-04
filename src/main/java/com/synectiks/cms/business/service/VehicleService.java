package com.synectiks.cms.business.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.enumeration.Status;
import com.synectiks.cms.graphql.types.Vehicle.AddVehicleInput;
import com.synectiks.cms.repository.TransportRouteRepository;
import com.synectiks.cms.repository.VehicleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.synectiks.cms.constant.CmsConstants;
import com.synectiks.cms.service.util.CommonUtil;
import com.synectiks.cms.service.util.DateFormatUtil;

@Component
public class VehicleService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    TransportRouteRepository transportRouteRepository;

    public List<CmsVehicleVo> getCmsVehicleList(Status status) {
        Vehicle vehicle = new Vehicle();
        vehicle.setStatus(status);
        List<Vehicle> list = this.vehicleRepository.findAll(Example.of(vehicle));
        List<CmsVehicleVo> ls = changeVehicleToCmsVehicleList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public List<Vehicle> getVehicleList(Status status) {
        Vehicle vehicle = new  Vehicle();
        vehicle.setStatus(status);
        List<Vehicle> list = this.vehicleRepository.findAll(Example.of(vehicle));
        Collections.sort(list, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return list;
    }

    public List<CmsVehicleVo> getVehicleList(){
        List<Vehicle> list = this.vehicleRepository.findAll();
        List<CmsVehicleVo> ls = changeVehicleToCmsVehicleList(list);
        Collections.sort(ls, (o1, o2) -> o2.getId().compareTo(o1.getId()));
        return ls;
    }

    public CmsVehicleVo getCmsVehicle(Long id){
        Optional<Vehicle> v = this.vehicleRepository.findById(id);
        if(v.isPresent()) {
            CmsVehicleVo vo = CommonUtil.createCopyProperties(v.get(), CmsVehicleVo.class);
            convertDatesAndProvideDependencies(v.get(), vo);
            logger.debug("CmsVehicle for given id : "+id+". CmsVehicle object : "+vo);
            return vo;
        }
        logger.debug("Vehicle object not found for the given id. "+id+". Returning null object");
        return null;
    }
    public Vehicle getVehicle(Long id){
        Optional< Vehicle> v = this.vehicleRepository.findById(id);
        if(v.isPresent()) {
            return v.get();
        }
        logger.debug("Vehicle object not found for the given id. "+id+". Returning null");
        return null;
    }
    private List<CmsVehicleVo> changeVehicleToCmsVehicleList(List<Vehicle> list){
        List<CmsVehicleVo> ls = new ArrayList<>();
        for(Vehicle v: list) {
            CmsVehicleVo vo = CommonUtil.createCopyProperties(v, CmsVehicleVo.class);
            convertDatesAndProvideDependencies(v, vo);
            ls.add(vo);
        }
        return ls;
    }

    private void convertDatesAndProvideDependencies(Vehicle v, CmsVehicleVo vo) {
        if(v.getDateOfRegistration() != null) {
            vo.setStrDateOfRegistration(DateFormatUtil.changeLocalDateFormat(v.getDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy));
            vo.setDateOfRegistration(null);
        }
    }

    public CmsVehicleVo addVehicle(AddVehicleInput input) {
        logger.info("Saving vehicle");
        CmsVehicleVo vo = null;
        try {
            Vehicle vehicle = null;
            if (input.getId() == null) {
                logger.debug("Adding new Vehicle");
                vehicle = CommonUtil.createCopyProperties(input, Vehicle.class);
            } else {
                logger.debug("Updating existing Vehicle");
                vehicle = this.vehicleRepository.findById(input.getId()).get();
            }
            TransportRoute tr = this.transportRouteRepository.findById(input.getTransportRouteId()).get();
            vehicle.setTransportRoute(tr);
            vehicle.setVehicleNumber(input.getVehicleNumber());
            vehicle.setVehicleType(input.getVehicleType());
            vehicle.setOwnerShip(input.getOwnerShip());
            vehicle.setCapacity(input.getCapacity());
            vehicle.setYearOfManufacturing(input.getYearOfManufacturing());
            vehicle.setManufacturingCompany(input.getManufacturingCompany());
            vehicle.setContactNumber(input.getContactNumber());
            vehicle.setChasisNo(input.getChasisNo());
            vehicle.setRcNo(input.getRcNo());
            vehicle.setModel(input.getModel());
            vehicle.setStatus(input.getStatus());
            vehicle.setDateOfRegistration(input.getStrDateOfRegistration() != null ? DateFormatUtil.convertStringToLocalDate(input.getStrDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : null);
            vehicle = this.vehicleRepository.save(vehicle);
            vo = CommonUtil.createCopyProperties(vehicle, CmsVehicleVo.class);
            vo.setStrDateOfRegistration(vehicle.getDateOfRegistration() != null ? DateFormatUtil.changeLocalDateFormat(vehicle.getDateOfRegistration(), CmsConstants.DATE_FORMAT_dd_MM_yyyy) : "");
            vo.setCreatedOn(null);
            vo.setUpdatedOn(null);
            vo.setExitCode(0L);
            if (input.getId() == null) {
                vo.setExitDescription("vehicle is added successfully");
                logger.debug("vehicle is added successfully");
            } else {
                vo.setExitDescription("vehicle is updated successfully");
                logger.debug("vehicle is updated successfully");
            }

        } catch (Exception e) {
            vo = new CmsVehicleVo();
            vo.setExitCode(1L);
            vo.setExitDescription("Due to some exception, vehicle data not be saved");
            logger.error("Vehicle save failed. Exception : ", e);
        }
        logger.info("Vehicle saved successfully");
        List ls = getVehicleList();
        vo.setDataList(ls);
        return vo;
    }
}





