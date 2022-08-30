package com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist;

import org.springframework.batch.core.annotation.*;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * packageName    : com.example.springbatch_13_2_chunklistener_itemreadlistener_itemprocesslist
 * fileName       : CustomChunkListener
 * author         : namhyeop
 * date           : 2022/08/25
 * description    :
 * ChunkListener 테스트 예제
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/08/25        namhyeop       최초 생성
 */
public class CustomChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext){
        System.out.println(">> before Chunk");
    }

    @AfterChunk
    public void afterChunk(ChunkContext chunkContext){
        System.out.println(">> after Chunk");
    }

    @AfterChunkError
    public void afterChunkError(ChunkContext chunkContext){
        System.out.println(">> after Chunk Error");
    }
}
