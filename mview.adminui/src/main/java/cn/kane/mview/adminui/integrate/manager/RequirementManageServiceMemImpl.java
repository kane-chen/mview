package cn.kane.mview.adminui.integrate.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

//import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import cn.kane.mview.adminui.integrate.entity.Requirement;
import cn.kane.mview.adminui.integrate.service.RequirementManageService;
@Service("requirementManageService")
public class RequirementManageServiceMemImpl implements RequirementManageService {

	private Map<String,Requirement> store = new ConcurrentHashMap<String,Requirement>() ;
	private AtomicLong counter = new AtomicLong(1) ;
	
//	@PostConstruct
	@Deprecated
	public void init(){
		Requirement req = new Requirement() ;
		req.setName("requirement");
		this.add(req) ;
	}
	
	@Override
	public String add(Requirement requirement) {
		if(null == requirement){
			return null;
		}
		Date now = new Date() ;
		if(null == requirement.getId()){
			requirement.setId(String.valueOf(counter.incrementAndGet())) ;
			requirement.setStatus("NEW") ;
			requirement.setCtime(now) ;
		}
		requirement.setMtime(now) ;
		store.put(requirement.getId(), requirement) ;
		return requirement.getId() ;
	}

	@Override
	public void edit(Requirement requirement) {
		if(!store.containsKey(requirement.getId())){
			throw new IllegalArgumentException(String.format("not found Requirement[id=%s]",requirement.getId()));
		}
		store.put(requirement.getId(), requirement) ;
	}

	@Override
	public Requirement get(String id) {
		return store.get(id);
	}

	@Override
	public void remove(String id) {
		if(!store.containsKey(id)){
			throw new IllegalArgumentException(String.format("not found Requirement[id=%s]",id));
		}
		store.remove(id) ;
	}
	

	/**
	 * non-ThreadSafe
	 */
	@Override
	public void compareAndSetStatus(String id,String status, String newStatus) {
		Requirement requirement = store.get(id) ;
		if(null == requirement){
			throw new IllegalArgumentException(String.format("Requirement[id=%s] not found ",id));
		}
		if(null!=status && !status.equalsIgnoreCase(requirement.getStatus())){
			throw new IllegalArgumentException(String.format("Requirement[id=%s]'s status is",id,requirement.getStatus()));
		}
		requirement.setStatus(newStatus) ;
		store.put(id, requirement) ;
	}

	@Override
	public List<Requirement> list(String name, Date fromDate,Date toDate) {
		return new ArrayList<Requirement>(store.values()) ;
	}


}
