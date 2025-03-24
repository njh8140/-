package com.onlineplatform.lctr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineplatform.lctr.model.LctrRprsVO;
import com.onlineplatform.lctr.service.LctrStatsSrchService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/lctrStatsSrch") // <-- API 경로 일관성 유지
public class LctrStatsSrchController {

	@Autowired
	public LctrStatsSrchService lctrStatsSrchService;
	
	@RequestMapping(value="", method= {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public List<LctrRprsVO> list(){
		List<LctrRprsVO> list = lctrStatsSrchService.getLctrRprsList();
		System.out.println("list size : " + list.size());
		return list;
	}
	
	@GetMapping("/lctrSeqReg/{lctrSeq}")
    public ResponseEntity<?> getLctrSeqReg(@PathVariable("lctrSeq") String lctrSeq) {
		// lctrSeq 값을 이용해 강좌 정보를 가져오는 로직 추가
	    LctrRprsVO lctrRprs = lctrStatsSrchService.getLctrRprsBySeq(lctrSeq);
	    
	    if (lctrRprs != null) {
	        return ResponseEntity.ok(lctrRprs);
	    } else {
	        return ResponseEntity.status(404).body("강좌 정보를 찾을 수 없습니다.");
	    }
    }
	
}
