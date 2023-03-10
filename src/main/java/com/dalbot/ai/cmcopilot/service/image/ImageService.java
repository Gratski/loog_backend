package com.dalbot.ai.cmcopilot.service.image;

import com.dalbot.ai.cmcopilot.dto.article.generate.GenerateArticleResponseDTO;
import com.dalbot.ai.cmcopilot.dto.image.GenerateImageRequestDTO;
import com.dalbot.ai.cmcopilot.dto.image.GenerateImageResponseDTO;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRepository;
import com.dalbot.ai.cmcopilot.repository.openai.OpenAIRequestEntities;
import com.dalbot.ai.cmcopilot.repository.storage.FileStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {

    @Value("${openai.api.key}")
    private String openAIApiKey;
    final FileStorageRepository fileStorageRepository;

    final OpenAIRepository openAIRepository;

    public ImageService(FileStorageRepository fileStorageRepository, OpenAIRepository openAIRepository) {
        this.fileStorageRepository = fileStorageRepository;
        this.openAIRepository = openAIRepository;
    }

    public GenerateImageResponseDTO generateImage(GenerateImageRequestDTO dto) {
        OpenAIRequestEntities.ImageGenerationRequest req = OpenAIRequestEntities.ImageGenerationRequest.builder()
                .size("1024x1024")
                .n(1)
                .prompt(dto.getPrompt())
                .build();
        OpenAIRequestEntities.ImageGenerationResponse resp = openAIRepository.createImage("Bearer " + openAIApiKey, req);
        return GenerateImageResponseDTO.builder()
                .imageUrl(resp.getData().get(0).getUrl())
                .build();
    }
    public GenerateArticleResponseDTO combineImages(
            MultipartFile logo, MultipartFile background, MultipartFile product) {
        return null;
    }

}
