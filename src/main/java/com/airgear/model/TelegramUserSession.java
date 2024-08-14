package com.airgear.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "telegram_user_sessions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramUserSession {
    @Id
    private long chatId;
    private String sessionData;
}
