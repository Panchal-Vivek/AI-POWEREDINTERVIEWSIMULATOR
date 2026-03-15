let sessionId = null;

document.getElementById("startBtn").addEventListener("click", startInterview);
document.getElementById("submitBtn").addEventListener("click", submitAnswer);

function startInterview() {
    fetch("http://localhost:8082/api/interview/start", {
        method: "POST"
    })
    .then(res => res.json())
    .then(data => {
        sessionId = data.sessionId;

        document.getElementById("sessionId").innerText = sessionId;
        document.getElementById("question").innerText =
            "What is Java and why is it platform independent?";
        document.getElementById("sessionBox").style.display = "block";
        document.getElementById("status").innerText = "In Progress";
    });
}

function submitAnswer() {
    const answer = document.getElementById("answer").value;
    const difficulty = document.getElementById("difficulty").value;

    fetch("http://localhost:8082/api/interview/submit", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            sessionId: sessionId,
            answer: answer,
            difficulty: parseInt(difficulty)
        })
    })
    .then(res => res.json())
    .then(data => {
        document.getElementById("score").innerText = data.score;
        document.getElementById("level").innerText = data.level ?? "-";
        document.getElementById("status").innerText =
            data.completed ? "Completed" : "In Progress";

        if (data.nextQuestion) {
            document.getElementById("question").innerText = data.nextQuestion;
        }

        document.getElementById("answer").value = "";

        if (data.completed) {
            document.getElementById("submitBtn").disabled = true;
        }
    });
}
