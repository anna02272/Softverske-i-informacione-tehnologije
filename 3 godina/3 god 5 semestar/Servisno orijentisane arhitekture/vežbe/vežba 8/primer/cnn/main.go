package main

import (
	"log"
	"net/http"
	"soa/2023-2024/lab7/cnn/domain"
	"soa/2023-2024/lab7/cnn/handlers"

	"github.com/gorilla/mux"
)

func main() {
	articleHandler := handlers.NewArticleHandler(articles)

	r := mux.NewRouter()

	r.HandleFunc("/articles", articleHandler.Search).
		Methods(http.MethodGet).
		Queries("query", "{query}")

	srv := &http.Server{
		Handler: r,
		Addr:    ":8000",
	}
	log.Fatalln(srv.ListenAndServe())
}

var articles = []domain.Article{
	{
		Title:   "Beekeepers to the rescue after 5 million bees fall off truck in Canada",
		Authors: []string{"Nadine Yousif"},
		Contents: `
		Beekeeper Michael Barber woke up on Wednesday morning to several calls from police looking for help after five million bees fell off a truck in Canada.
		The hives were being transported when the straps holding them in place came loose, allowing them to slip free.
		Mr Barber said he arrived to "a pretty crazy cloud of bees" who were "very angry, confused and homeless".
		Drivers were told to keep their windows up and pedestrians to stay away.
		The scene in Burlington, Ontario was unlike anything Mr Barber has experienced in his 11-year career.
		"It was something else," he told the BBC. "I hope to never experience it again."
		Mr Barber, who owns Tri-City Bee Rescue in nearby Guelph, said he first received the calls from local police at around 07:00 local time (12:00 GMT), informing him that there was an accident that resulted in bee hives being spread all over the road.
		At the same time police put out a public call on social media urging people to stay away from the area, which is about an hour south of Toronto.
		The bees were in their hives packed up on the back of a truck and being transported to their wintering location when the accident happened.
		As soon as Mr Barber heard from police, he put out a call to other beekeepers for additional help. About a dozen beekeepers eventually helped corral the insects.
		Mr Barber said the bees and their hives were scattered over a 400-meter (1,300-foot) radius. On nearby cars and mail posts some of the younger bees were clustering, which he said they do when they are seeking safety.
		"There were probably a thousand bees on the front of my truck," he said.
		Other bees, ones that were angrier and older, were buzzing around.
		After a few hours, most of the bees were able to find their hives, Mr Barber said, but a few hundred bees did not survive the accident.
		Some beekeepers were also stung.
		The driver of the truck was stung more than 100 times, Mr Barber said, as he wasn't wearing a full beekeeper suit. Paramedics were nearby and he was not seriously injured.
		"There were a lot of flying bees that made even beekeepers in full suits nervous," he said.
		He said he was grateful for the many local beekeepers that worked to keep the insects and the public safe, and added that the incident is a good reminder to always securely strap your bees.
		"Lesson learned. Everybody survived and a few bees were hurt," he said. "Hopefully the hives will survive the winter."
	`,
	},
	{
		Title:   "The Crown: Netflix's royal drama featuring Diana's ghost savaged by critics",
		Authors: []string{"Yasmin Rufo"},
		Contents: `
		The first four episodes of the final series of Netflix's The Crown have been given the thumbs down by most critics
		Season six of the royal drama depicts the events of the late 1990s, including Princess Diana's relationship with Dodi Fayed and her death.
		It also covers the aftermath of her death in which 'ghost Diana' appears to Prince Charles and the Queen.
		In a one star review, the Guardian said the "Diana-obsessed series is the very definition of bad writing".
		"Beyond all its formal failures, late-period Crown is also impossibly hamstrung by being set well within living memory. Even if there were anything to engage with, the memories and consequent questions that crowd into the viewer's mind at every stage would make it impossible," wrote Lucy Mangan.
		"It started teetering in season three, lost its balance entirely over the next two and is now plummeting into the abyss."
		She added that this was "despite the uniformly brilliant performances from the entire cast".
		Australian actress Debicki, who plays Princes Diana, has also appeared in Tenet, The Great Gatsby and Guardians of the Galaxy
		Anita Singh of The Telegraph echoed the Guardian, writing that the "Netflix jewel hits a dead end" as the new season is "haunted by Princess Diana's bizarre ghost".
		The two star review notes that the use of Diana's ghost "on the plane home from Paris to comfort a distraught Prince of Wales, and on the sofa at Balmoral to give the Queen some friendly PR advice", ends up sounding "like desperation on the part of writer Peter Morgan" who created the hugely popular show which has been running since 2016.
		Singh also criticises the handling of the the car crash scene, writing: "The chaos of Diana and Dodi's final day in Paris is conveyed but there are no scenes inside the Pont d'Alma tunnel: we cut from the sound of the crash to the phone ringing at Balmoral. All dialogue in which someone breaks the news of Diana's death has been dubbed out; their mouths move in silence, and we focus on the reactions.
		"Why do this? If it's for reasons of taste, why have the camera capture the bewildered face of Harry as he mouths the word "no"? Good taste would mean leaving this scene to our imagination".
		Despite a four star review from The Times, Carol Midgley notes that Diana's ghost "wasn't the show's finest hour" and was "peculiarly self-defeating in an otherwise powerful and moving opening four-episode suite".
		However, the review goes on to praise Elizabeth Debicki's performance as Diana, calling it "outstanding".
		"The empathy with which she portrays the last eight weeks of Diana's life and the likeness to Diana is extraordinary, that flirty cocking of the head, a slightly lost, lonely soul who ends up in various swimsuits in the gaudy Hello! mag environs of Mohamed Al Fayed's yacht."
		New images show Elizabeth Debicki as Princess Diana and Khalid Abdalla as Dodi Fayed sitting in a car
		The new season covers the media frenzy around Diana and Dodi's (Khalid Abdalla) relationship, culminating in the paparazzi chase that caused the car they were both in to crash in a tunnel in Paris. Both died on 31 August 1997.
		The series also portrays the immediate events following the fatal car accident, including reactions and responses from the Queen and Al Fayed and Prince William trying to integrate back into life at Eton following his mother's death.
		Speaking at the Edinburgh TV Festival earlier this year, producers said the subject of Diana's death has been treated "sensitively".
		However, Time Magazine's Judy Berman wrote that the new season is "weirdly audacious" as it is "milking the mystery of Diana's last days - as well as, unfortunately, her imagined afterlife - for manufactured poignancy. Like the tragedy on which it fixates, it's a wreck on a scale that the show has never seen before."
	`,
	},
}
