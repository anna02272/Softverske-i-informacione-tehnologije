package poststore

type RequestPost struct {
	Id      string   `json:"id"`
	Title   string   `json:"title"`
	Text    string   `json:"text"`
	Tags    []string `json:"tags"`
	Labels  string   `json:"labels"`
	Version string   `json:"version"`
}
