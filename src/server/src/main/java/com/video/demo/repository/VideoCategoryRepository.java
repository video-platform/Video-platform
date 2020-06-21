package com.video.demo.repository;

import com.video.demo.domain.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCategoryRepository extends JpaRepository<VideoCategory,Long> {
}
