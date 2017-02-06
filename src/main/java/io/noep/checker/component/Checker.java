package io.noep.checker.component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by NOEP-2016 on 2017-02-06.
 */

@Slf4j
@Component
@RequiredArgsConstructor
public class Checker {

    private final RestTemplate restTemplate;

    private final static String url = "http://dummy";

    private int saved;

    @Scheduled(fixedRate = 300000) //-- 300초 5분
    public void checkUrl() {

        String htmlString = restTemplate.getForObject(url, String.class);
        Document doc = Jsoup.parse(htmlString);
        List<Element> elements = doc.getElementsByClass("inContent")
                .get(0)
                .getElementsByClass("tableRow")
                .stream()
                .filter(element -> element.childNodeSize() == 9)
                .collect(Collectors.toList());
        log.info("{}", elements);

        List<String> numbers = elements.stream()
                .map(element -> element.childNodes().get(1).childNode(0).toString().replace(",","").replace("\n",""))
                .collect(toList());
        log.info("{}", numbers);

        int newest = Integer.parseInt(numbers.get(0));

        if(saved != 0 && saved != newest) {
            saved = newest;
            //--todo 알림이 필요해
        }
    }

}
