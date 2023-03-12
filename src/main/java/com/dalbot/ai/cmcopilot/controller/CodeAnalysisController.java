package com.dalbot.ai.cmcopilot.controller;

import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisRequestDTO;
import com.dalbot.ai.cmcopilot.dto.code.CodeAnalysisResponseDTO;
import com.dalbot.ai.cmcopilot.service.code.CodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/code")
public class CodeAnalysisController {

    private final CodeService service;

    public CodeAnalysisController(CodeService service) {
        this.service = service;
    }

    @RequestMapping(path = "/analysis2", method = RequestMethod.POST)
    public ResponseEntity<CodeAnalysisResponseDTO> codeAnalysisReport2(@RequestBody CodeAnalysisRequestDTO dto){
        String analysis = service.performCodeAnalysis(dto);
        CodeAnalysisResponseDTO result = CodeAnalysisResponseDTO.builder()
                .result(analysis)
                .build();
        return ResponseEntity.ok(result);
    }

}
