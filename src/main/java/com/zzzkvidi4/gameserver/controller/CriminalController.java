package com.zzzkvidi4.gameserver.controller;

import com.zzzkvidi4.gameserver.model.Criminal;
import com.zzzkvidi4.gameserver.model.CriminalPK;
import com.zzzkvidi4.gameserver.response.Error;
import com.zzzkvidi4.gameserver.response.JsonHttpResponse;
import com.zzzkvidi4.gameserver.service.CriminalService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CriminalController {
    @Resource(name = "criminalService")
    private CriminalService criminalService;

    @RequestMapping(path = "/criminals", method = RequestMethod.GET)
    public JsonHttpResponse<List<Criminal>> getCriminals(){
        JsonHttpResponse<List<Criminal>> response = new JsonHttpResponse<>();
        response.setData(criminalService.getAll());
        return response;
    }

    @RequestMapping(path = "/criminals/{id}_{character_id}", method = RequestMethod.GET)
    public JsonHttpResponse<Criminal> getCriminal(@PathVariable(name = "id") int id, @PathVariable(name = "character_id") int characterId){
        JsonHttpResponse<Criminal> response = new JsonHttpResponse<>();
        response.setData(criminalService.findById(new CriminalPK(id, characterId)));
        if (response.getData() == null) {
            response.addError(new Error("criminal_id", "Criminal with this id doesn't exists!"));
        }
        return response;
    }

    @RequestMapping(path = "/criminals", method = RequestMethod.POST)
    public JsonHttpResponse<Void> createCriminal(@RequestBody Criminal criminal){
        JsonHttpResponse<Void> response = new JsonHttpResponse<>();
        response.addErrors(criminalService.create(criminal));
        return response;
    }
}
