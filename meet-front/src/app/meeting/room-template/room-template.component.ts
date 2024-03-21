import {Component, ElementRef, Renderer2} from '@angular/core';

@Component({
  selector: 'app-room-template',
  standalone: true,
  imports: [],
  templateUrl: './room-template.component.html',
  styleUrl: './room-template.component.css'
})
export class RoomTemplateComponent {
  constructor(private renderer: Renderer2, private el: ElementRef) { }

  ngOnInit(): void {
    this.loadHTMLAndScripts();
  }

  loadHTMLAndScripts(): void {
    const roomHtmlPath = 'assets/htmlTemp/room.html';

    // Load HTML content
    fetch(roomHtmlPath)
      .then(response => response.text())
      .then(html => {
        const parser = new DOMParser();
        const parsedHtml = parser.parseFromString(html, 'text/html');
        const roomContainer = parsedHtml.querySelector('#room__container');

        if (roomContainer) {
          // Append the loaded HTML content to the component's native element
          this.renderer.appendChild(this.el.nativeElement, roomContainer);

          this.loadStyles('assets/styles/main.css');
          this.loadStyles('assets/styles/room.css');

          // Load JavaScript files
          console.log('Loading scripts')
          this.loadScript('assets/jsTemp/agora-rtm-sdk-1.4.4.js');
          console.log('1')
          this.loadScript('assets/jsTemp/AgoraRTC_N-4.11.0.js');
          console.log('2')
          this.loadScript('assets/jsTemp/room.js');
          console.log('3')
          this.loadScript('assets/jsTemp/room_rtm.js');
          console.log('4')
          this.loadScript('assets/jsTemp/room_rtc.js');
          console.log('5')
        }
      });
  }

  loadStyles(stylePath: string): void {
    const link = this.renderer.createElement('link');
    link.rel = 'stylesheet';
    link.type = 'text/css';
    link.href = stylePath;
    this.renderer.appendChild(document.head, link);
  }

  loadScript(scriptPath: string): void {
    const script = this.renderer.createElement('script');
    script.src = scriptPath;
    script.type = 'text/javascript';
    script.async = true;
    script.defer = true;
    this.renderer.appendChild(document.body, script);
    console.log('Script loaded' + scriptPath);
  }
}
