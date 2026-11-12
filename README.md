<p align="center">
  <a href="https://github.com/NoyaAdivi/HaMivchanon"><img src="docs/readme/banner-en.svg" width="100%" alt="HaMivchanon: an Android study management app"></a>
</p>

<p align="center"><a href="README.he.md"><img src="docs/readme/lang-he.svg" height="44" alt="לגרסה בעברית"></a></p>

<p align="center">
  <a href="HaMivchanon.pdf"><img src="https://img.shields.io/badge/Project_portfolio-FFD5C9?style=for-the-badge&logo=adobeacrobatreader&logoColor=A8483A" alt="Project portfolio"></a>
  <a href="exams1.png"><img src="https://img.shields.io/badge/Class_diagram-DAD5FF?style=for-the-badge&logo=uml&logoColor=5548C8" alt="Class diagram"></a>
  <a href="src/main/java/com/example/user"><img src="https://img.shields.io/badge/View_code-D9F3E7?style=for-the-badge&logo=android&logoColor=1F7350" alt="View code"></a>
</p>

<h3 align="center">Reminders, materials and time management for students, in one app.</h3>

<p align="center">
  <p align="center"><a href="HaMivchanon.pdf"><img src="src/main/res/drawable-v24/logo.png" width="150" alt="HaMivchanon logo"></a></p>
  A native Android app built as a 5-unit Computer Science final project: smart study reminders,<br>a workspace for every subject, and photos and documents synced to the cloud.
</p>

<table align="center">
  <tr>
    <td align="center" width="25%"><a href="#highlights"><b>⏰ Smart</b></a><br><sub>study reminders</sub></td>
    <td align="center" width="25%"><a href="#highlights"><b>📚 1</b></a><br><sub>workspace per subject</sub></td>
    <td align="center" width="25%"><a href="#tech-stack"><b>☁️ Firebase</b></a><br><sub>database + storage</sub></td>
    <td align="center" width="25%"><a href="HaMivchanon.pdf"><b>📐 UML</b></a><br><sub>full design portfolio</sub></td>
  </tr>
</table>

<a name="highlights"></a>
<p align="center"><a href="#highlights"><img src="docs/readme/highlights-en.svg" width="100%" alt="Highlights"></a></p>

<table>
  <tr valign="top">
    <td width="33%"><p><b>⏰ Smart study reminders</b></p><ul><li><b>Local notifications</b> with <code>WorkManager</code> and <code>BroadcastReceiver</code></li><li><b>Date ranges and set times</b> chosen by the student</li><li><b>Animated clocks</b> with Lottie</li></ul></td>
    <td width="33%"><p><b>📚 My Class workspace</b></p><ul><li><b>One area per subject</b> for all its materials</li><li><b>Camera, gallery or PDF</b> straight from the device</li><li><b>Zoomable images</b> for reading notes</li></ul></td>
    <td width="33%"><p><b>☁️ Synced to the cloud</b></p><ul><li><b>Firebase Realtime Database</b> for reminders and notes</li><li><b>Firebase Storage</b> for photos and documents</li><li><b>Simple sign-in</b> by email, kept in <code>SharedPreferences</code></li></ul></td>
  </tr>
</table>

<a name="how-it-works"></a>
<p align="center"><a href="#how-it-works"><img src="docs/readme/how-it-works-en.svg" width="100%" alt="How it works"></a></p>

```mermaid
flowchart LR
  A([🧑‍🎓 Student]) --> B[New reminder<br/>subject · dates · times]
  B --> C[WorkManager<br/>schedules the work]
  C --> D([🔔 Notification<br/>at the right time])
  A --> E[My Class<br/>camera · gallery · PDF]
  E --> F[(Firebase Storage)]
  B --> G[(Firebase Realtime DB)]
  style A fill:#E8E4FF,stroke:#6F60F0,color:#2C2668
  style D fill:#D9F3E7,stroke:#1F7350,color:#1F4D38
  style F fill:#FFE6DE,stroke:#E36E57,color:#2C2668
  style G fill:#FFE6DE,stroke:#E36E57,color:#2C2668
```

<p align="center"><a href="exams1.png"><img src="exams1.png" width="100%" alt="Class diagram"></a><br><sub>Class diagram: activities, adapters, models and workers (source in <a href="app.plantuml"><code>app.plantuml</code></a>)</sub></p>

The [project portfolio](HaMivchanon.pdf) has the full design: use case and sequence diagrams, UML class diagrams, the database structure with JSON models, and a top-down design.

<a name="tech-stack"></a>
<p align="center"><a href="#tech-stack"><img src="docs/readme/tech-stack-en.svg" width="100%" alt="Tech Stack"></a></p>

<p align="center">
  <a href="https://dev.java"><img src="https://img.shields.io/badge/Java-CFE6FA?style=for-the-badge&logo=openjdk&logoColor=3F6D9F" alt="Java"></a>
  <a href="https://developer.android.com"><img src="https://img.shields.io/badge/Android-D9F3E7?style=for-the-badge&logo=android&logoColor=1F7350" alt="Android"></a>
  <a href="https://firebase.google.com"><img src="https://img.shields.io/badge/Firebase-FFD5C9?style=for-the-badge&logo=firebase&logoColor=A8483A" alt="Firebase"></a>
  <a href="https://developer.android.com/topic/libraries/architecture/workmanager"><img src="https://img.shields.io/badge/WorkManager-DAD5FF?style=for-the-badge&logo=android&logoColor=5548C8" alt="WorkManager"></a>
  <a href="https://airbnb.io/lottie"><img src="https://img.shields.io/badge/Lottie-FBD5E0?style=for-the-badge&logo=airbnb&logoColor=A03A5C" alt="Lottie"></a>
  <a href="https://plantuml.com"><img src="https://img.shields.io/badge/PlantUML-FFD5C9?style=for-the-badge&logo=uml&logoColor=A8483A" alt="PlantUML"></a>
</p>

<a name="run-it"></a>
<p align="center"><a href="#run-it"><img src="docs/readme/run-it-en.svg" width="100%" alt="Run it"></a></p>

1. Clone the repository and open the folder in **Android Studio**.
2. Let Gradle sync ([`build.gradle`](build.gradle)).
3. Add your own `google-services.json` from a Firebase project (Realtime Database and Storage turned on).
4. Run on a device or emulator with Android 8.1 (API 27) or newer.

<a name="contact"></a>
<p align="center"><a href="#contact"><img src="docs/readme/footer-en.svg" width="100%" alt="Built by Noya Adivi"></a></p>

<p align="center">
  <a href="https://github.com/NoyaAdivi/HaMivchanon"><img src="https://img.shields.io/badge/Open_project-FFD5C9?style=for-the-badge&logo=github&logoColor=A8483A" alt="Open project"></a>
  <a href="https://www.linkedin.com/in/noya-adivi/"><img src="https://img.shields.io/badge/LinkedIn-DAD5FF?style=for-the-badge&logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNCAyNCI+PHBhdGggZmlsbD0iIzU1NDhDOCIgZD0iTTIwLjQ1IDIwLjQ1aC0zLjU2di01LjU3YzAtMS4zMy0uMDMtMy4wNC0xLjg1LTMuMDQtMS44NiAwLTIuMTQgMS40NS0yLjE0IDIuOTR2NS42N0g5LjM1VjloMy40MXYxLjU2aC4wNWMuNDgtLjkgMS42NC0xLjg1IDMuMzctMS44NSAzLjYgMCA0LjI3IDIuMzcgNC4yNyA1LjQ2djYuMjh6TTUuMzQgNy40M2EyLjA2IDIuMDYgMCAxIDEgMC00LjEzIDIuMDYgMi4wNiAwIDAgMSAwIDQuMTN6TTcuMTIgMjAuNDVIMy41NlY5aDMuNTZ2MTEuNDV6TTIyLjIyIDBIMS43N0MuNzkgMCAwIC43NyAwIDEuNzN2MjAuNTRDMCAyMy4yMy43OSAyNCAxLjc3IDI0aDIwLjQ1Yy45OCAwIDEuNzgtLjc3IDEuNzgtMS43M1YxLjczQzI0IC43NyAyMy4yIDAgMjIuMjIgMHoiLz48L3N2Zz4=" alt="LinkedIn"></a>
  <a href="https://noyaadivi.github.io/noya-website"><img src="https://img.shields.io/badge/Portfolio-D9F3E7?style=for-the-badge&logo=githubpages&logoColor=1F7350" alt="Portfolio"></a>
  <a href="https://github.com/NoyaAdivi"><img src="https://img.shields.io/badge/More_projects-FBD5E0?style=for-the-badge&logo=github&logoColor=A03A5C" alt="More projects"></a>
</p>
