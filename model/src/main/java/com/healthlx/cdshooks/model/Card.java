package com.healthlx.cdshooks.model;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Card {

  private String summary;
  private String detail;
  private IndicatorEnum indicator;
  private Source source;
  private List<Suggestion> suggestions = new ArrayList<>();
  private String selectionBehavior;
  private List<Link> links = new ArrayList<>();

  public Card addSuggestionsItem(Suggestion suggestionsItem) {
    this.suggestions.add(suggestionsItem);
    return this;
  }

  public Card addLinksItem(Link linksItem) {
    this.links.add(linksItem);
    return this;
  }
}
