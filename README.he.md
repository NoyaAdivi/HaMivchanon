<div dir="rtl">

<p align="center">
  <a href="https://github.com/NoyaAdivi/HaMivchanon"><img src="docs/readme/banner-he.svg" width="100%" alt="המבחנון: אפליקציית אנדרואיד לניהול למידה"></a>
</p>

<p align="center"><a href="README.md"><img src="docs/readme/lang-en.svg" height="44" alt="Read in English"></a></p>

<p align="center">
  <a href="HaMivchanon.pdf"><img src="https://img.shields.io/badge/%D7%AA%D7%99%D7%A7_%D7%94%D7%A4%D7%A8%D7%95%D7%99%D7%A7%D7%98-FFD5C9?style=for-the-badge&logo=adobeacrobatreader&logoColor=A8483A" alt="תיק הפרויקט"></a>
  <a href="exams1.png"><img src="https://img.shields.io/badge/%D7%AA%D7%A8%D7%A9%D7%99%D7%9D_%D7%9E%D7%97%D7%9C%D7%A7%D7%95%D7%AA-DAD5FF?style=for-the-badge&logo=uml&logoColor=5548C8" alt="תרשים מחלקות"></a>
  <a href="src/main/java/com/example/user"><img src="https://img.shields.io/badge/%D7%9C%D7%A7%D7%95%D7%93-D9F3E7?style=for-the-badge&logo=android&logoColor=1F7350" alt="לקוד"></a>
</p>

<h3 align="center">תזכורות, חומרי לימוד וניהול זמן לסטודנטים, באפליקציה אחת.</h3>

<p align="center">
  <p align="center"><a href="HaMivchanon.pdf"><img src="src/main/res/drawable-v24/logo.png" width="150" alt="הלוגו של המבחנון"></a></p>
  אפליקציית אנדרואיד שנבנתה כפרויקט גמר של 5 יחידות במדעי המחשב: תזכורות לימוד חכמות,<br>מרחב עבודה לכל מקצוע, ותמונות ומסמכים שמסונכרנים לענן.
</p>

<table align="center">
  <tr>
    <td align="center" width="25%"><a href="#highlights"><b>⏰ חכמות</b></a><br><sub>תזכורות לימוד</sub></td>
    <td align="center" width="25%"><a href="#highlights"><b>📚 1</b></a><br><sub>מרחב לכל מקצוע</sub></td>
    <td align="center" width="25%"><a href="#tech-stack"><b>☁️ Firebase</b></a><br><sub>מסד נתונים ואחסון</sub></td>
    <td align="center" width="25%"><a href="HaMivchanon.pdf"><b>📐 UML</b></a><br><sub>תיק תכנון מלא</sub></td>
  </tr>
</table>

<a name="highlights"></a>
<p align="center"><a href="#highlights"><img src="docs/readme/highlights-he.svg" width="100%" alt="מה מיוחד בו"></a></p>

<table>
  <tr valign="top">
    <td width="33%"><p><b>⏰ תזכורות לימוד חכמות</b></p><ul><li><b>התראות מקומיות</b> עם <code>WorkManager</code> ו־<code>BroadcastReceiver</code></li><li><b>טווח תאריכים ושעות</b> שהתלמיד בוחר</li><li><b>שעונים מונפשים</b> עם Lottie</li></ul></td>
    <td width="33%"><p><b>📚 הכיתה שלי</b></p><ul><li><b>מרחב לכל מקצוע</b> עם כל החומרים שלו</li><li><b>מצלמה, גלריה או PDF</b> ישר מהמכשיר</li><li><b>תמונות עם זום</b> לקריאת סיכומים</li></ul></td>
    <td width="33%"><p><b>☁️ מסונכרן לענן</b></p><ul><li><b>Firebase Realtime Database</b> לתזכורות ולפתקים</li><li><b>Firebase Storage</b> לתמונות ולמסמכים</li><li><b>כניסה פשוטה</b> עם מייל, שנשמר ב־<code>SharedPreferences</code></li></ul></td>
  </tr>
</table>

<a name="how-it-works"></a>
<p align="center"><a href="#how-it-works"><img src="docs/readme/how-it-works-he.svg" width="100%" alt="איך זה עובד"></a></p>

```mermaid
flowchart RL
  A(["🧑‍🎓 תלמיד"]) --> B["תזכורת חדשה<br/>מקצוע · תאריכים · שעות"]
  B --> C["WorkManager<br/>מתזמן את העבודה"]
  C --> D(["🔔 התראה<br/>בזמן הנכון"])
  A --> E["הכיתה שלי<br/>מצלמה · גלריה · PDF"]
  E --> F[("Firebase Storage")]
  B --> G[("Firebase Realtime DB")]
  style A fill:#E8E4FF,stroke:#6F60F0,color:#2C2668
  style D fill:#D9F3E7,stroke:#1F7350,color:#1F4D38
  style F fill:#FFE6DE,stroke:#E36E57,color:#2C2668
  style G fill:#FFE6DE,stroke:#E36E57,color:#2C2668
```

<p align="center"><a href="exams1.png"><img src="exams1.png" width="100%" alt="תרשים מחלקות"></a><br><sub>תרשים המחלקות: מסכים, מתאמים, מודלים ו־Workers (המקור ב־<a href="app.plantuml"><code>app.plantuml</code></a>)</sub></p>

ב[תיק הפרויקט](HaMivchanon.pdf) יש את כל התכנון: תרשימי Use Case ורצף, תרשימי מחלקות UML, מבנה מסד הנתונים עם מודלים של JSON ותכנון מלמעלה למטה.

<a name="tech-stack"></a>
<p align="center"><a href="#tech-stack"><img src="docs/readme/tech-stack-he.svg" width="100%" alt="טכנולוגיות"></a></p>

<p align="center">
  <a href="https://dev.java"><img src="https://img.shields.io/badge/Java-CFE6FA?style=for-the-badge&logo=openjdk&logoColor=3F6D9F" alt="Java"></a>
  <a href="https://developer.android.com"><img src="https://img.shields.io/badge/Android-D9F3E7?style=for-the-badge&logo=android&logoColor=1F7350" alt="Android"></a>
  <a href="https://firebase.google.com"><img src="https://img.shields.io/badge/Firebase-FFD5C9?style=for-the-badge&logo=firebase&logoColor=A8483A" alt="Firebase"></a>
  <a href="https://developer.android.com/topic/libraries/architecture/workmanager"><img src="https://img.shields.io/badge/WorkManager-DAD5FF?style=for-the-badge&logo=android&logoColor=5548C8" alt="WorkManager"></a>
  <a href="https://airbnb.io/lottie"><img src="https://img.shields.io/badge/Lottie-FBD5E0?style=for-the-badge&logo=airbnb&logoColor=A03A5C" alt="Lottie"></a>
  <a href="https://plantuml.com"><img src="https://img.shields.io/badge/PlantUML-FFD5C9?style=for-the-badge&logo=uml&logoColor=A8483A" alt="PlantUML"></a>
</p>

<a name="run-it"></a>
<p align="center"><a href="#run-it"><img src="docs/readme/run-it-he.svg" width="100%" alt="הרצה"></a></p>

1. משכפלים את הריפו ופותחים את התיקייה ב־**Android Studio**.
2. מחכים ש־Gradle יסתנכרן ([`build.gradle`](build.gradle)).
3. מוסיפים `google-services.json` מפרויקט Firebase משלכם (עם Realtime Database ו־Storage).
4. מריצים על מכשיר או אמולטור עם אנדרואיד 8.1 (API 27) ומעלה.

<a name="contact"></a>
<p align="center"><a href="#contact"><img src="docs/readme/footer-he.svg" width="100%" alt="נבנה על ידי נויה אדיבי"></a></p>

<p align="center">
  <a href="https://github.com/NoyaAdivi/HaMivchanon"><img src="https://img.shields.io/badge/%D7%9C%D7%A4%D7%A8%D7%95%D7%99%D7%A7%D7%98-FFD5C9?style=for-the-badge&logo=github&logoColor=A8483A" alt="לפרויקט"></a>
  <a href="https://www.linkedin.com/in/noya-adivi/"><img src="https://img.shields.io/badge/LinkedIn-DAD5FF?style=for-the-badge&logo=data:image/svg%2bxml;base64,PHN2ZyB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHZpZXdCb3g9IjAgMCAyNCAyNCI+PHBhdGggZmlsbD0iIzU1NDhDOCIgZD0iTTIwLjQ1IDIwLjQ1aC0zLjU2di01LjU3YzAtMS4zMy0uMDMtMy4wNC0xLjg1LTMuMDQtMS44NiAwLTIuMTQgMS40NS0yLjE0IDIuOTR2NS42N0g5LjM1VjloMy40MXYxLjU2aC4wNWMuNDgtLjkgMS42NC0xLjg1IDMuMzctMS44NSAzLjYgMCA0LjI3IDIuMzcgNC4yNyA1LjQ2djYuMjh6TTUuMzQgNy40M2EyLjA2IDIuMDYgMCAxIDEgMC00LjEzIDIuMDYgMi4wNiAwIDAgMSAwIDQuMTN6TTcuMTIgMjAuNDVIMy41NlY5aDMuNTZ2MTEuNDV6TTIyLjIyIDBIMS43N0MuNzkgMCAwIC43NyAwIDEuNzN2MjAuNTRDMCAyMy4yMy43OSAyNCAxLjc3IDI0aDIwLjQ1Yy45OCAwIDEuNzgtLjc3IDEuNzgtMS43M1YxLjczQzI0IC43NyAyMy4yIDAgMjIuMjIgMHoiLz48L3N2Zz4=" alt="LinkedIn"></a>
  <a href="https://noyaadivi.github.io/noya-website"><img src="https://img.shields.io/badge/%D7%AA%D7%99%D7%A7_%D7%A2%D7%91%D7%95%D7%93%D7%95%D7%AA-D9F3E7?style=for-the-badge&logo=githubpages&logoColor=1F7350" alt="תיק עבודות"></a>
  <a href="https://github.com/NoyaAdivi"><img src="https://img.shields.io/badge/%D7%A2%D7%95%D7%93_%D7%A4%D7%A8%D7%95%D7%99%D7%A7%D7%98%D7%99%D7%9D-FBD5E0?style=for-the-badge&logo=github&logoColor=A03A5C" alt="עוד פרויקטים"></a>
</p>

</div>
