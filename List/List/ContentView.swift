//
//  ContentView.swift
//  List
//
//  Created by AM Student on 9/9/22.
//

import SwiftUI

struct ContentView: View {
  @State  var alertIsVisible = false
    
    var body: some View {
        
        VStack{
        Text("My Favorites")
            .padding()
            Button(action: {
            withAnimation {
                self.alertIsVisible = true
                }
            }) {
                Text("Color")
                .bold()
                .font(.title3)
                    }
            .alert(isPresented:
                    $alertIsVisible,
                   content: { let Color = String(self.alertIsVisible)
                return Alert(title: Text("Red"))
                
            })
            
            Button(action: {
            withAnimation {
                self.alertIsVisible = true
                }
            }) {
                Text("Musician")
                .bold()
                .font(.title3)
                    }
            .alert(isPresented:
                    $alertIsVisible,
                   content: { let Musician = String(self.alertIsVisible)
                return Alert(title: Text("Ghost and Pals"))
                
            })
            Button(action: {
            withAnimation {
                self.alertIsVisible = true
                }
            }) {
                Text("TV Show")
                .bold()
                .font(.title3)
                    }
            .alert(isPresented:
                    $alertIsVisible,
                   content: { let Show = String(self.alertIsVisible)
                return Alert(title: Text("Fairy Tail"))
                
            })
            Button(action: {
            withAnimation {
                self.alertIsVisible = true
                }
            }) {
                Text("Food")
                .bold()
                .font(.title3)
                    }
            .alert(isPresented:
                    $alertIsVisible,
                   content: { let Food = String(self.alertIsVisible)
                return Alert(title: Text("Chicken"))
                
            })
            Button(action: {
            withAnimation {
                self.alertIsVisible = true
                }
            }) {
                Text("Collection")
                .bold()
                .font(.title3)
                    }
            .alert(isPresented:
                    $alertIsVisible,
                   content: { let Collection = String(self.alertIsVisible)
                return Alert(title: Text("Knives"))
                
            })
                   }
            }
            
        }
struct HitmeButton: View {
    @Binding var alertIsVisible: Bool
    
    var body: some View{
        VStack {
        }
    }
}

//struct ContentView_Previews: PreviewProvider {
//    static var previews: some View {
//        ContentView()
//    }
//}
