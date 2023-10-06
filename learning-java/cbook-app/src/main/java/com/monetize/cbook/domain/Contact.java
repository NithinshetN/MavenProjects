package com.monetize.cbook.domain;

import lombok.*;

import java.util.UUID;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Contact {
  private UUID id;
  private String firstName;
  private String lastName;
  private String mobile;
  private String email;
  private boolean deleted;
}
