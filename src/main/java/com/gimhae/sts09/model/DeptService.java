package com.gimhae.sts09.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gimhae.sts09.model.entity.Dept02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DeptService {
	@Autowired
	DeptRepo deptRepo;

	public List<Dept02> getList(){
		Iterable<Dept02> ite=deptRepo.findAll();
		List<Dept02> list=new ArrayList<>();
		ite.forEach(ele->list.add(ele));
		return list;
	}

	public void addList(DeptVo bean) {

		deptRepo.save(Dept02.builder()
				.dname(bean.getDname())
				.loc(bean.getLoc())
				.build());
	}

	public DeptVo getOne(int deptno) {
		log.debug("getOne no:"+deptno);
		Optional<Dept02> entity = deptRepo.findById(deptno);
		log.debug("getOne:"+entity.toString());
		log.debug("getOne:"+entity.isEmpty()+"");
		if(!entity.isEmpty()) {
			DeptVo bean = DeptVo.builder()
					.deptno(entity.get().getDeptno())
					.dname(entity.get().getDname())
					.loc(entity.get().getLoc())
					.build();
			log.debug("getOne:"+bean.toString());
			return bean;
		}
		return null;
	}

	public void editOne(DeptVo bean) {

		Dept02 entity=deptRepo.findById(bean.getDeptno()).get();
		entity.setDname(bean.getDname());
		entity.setLoc(bean.getLoc());
		deptRepo.save(entity);
	}

	public void deleteOne(int deptno) {

//		Dept02 entity=deptRepo.findById(deptno).get();
//		deptRepo.delete(entity);
		deptRepo.deleteById(deptno);
	}
}
