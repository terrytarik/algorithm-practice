import math
from queue import PriorityQueue


class Graph:
    def __init__(self):
        self.connections = {}

    def create_edges_from_vertexes(self, first_vertex_id: int, second_vertex_id: int, weight: int):
        # create vertex
        self.create_edge(first_vertex_id, second_vertex_id, weight)
        self.create_edge(second_vertex_id, first_vertex_id, weight)

    def create_edge(self, vertex_from: int, vertex_to: int, weight: int):
        # check if vertex not exists already
        if vertex_from in self.connections.keys():
            # check if the connection between these two vertexes not exists already
            if (vertex_to, weight) not in self.connections[vertex_from]:
                # add connection from 'vertex_from' to 'vertex_to with' weight 'weight'
                self.connections[vertex_from].append((vertex_to, weight))
        else:
            self.connections[vertex_from] = [(vertex_to, weight)]

    def print_graph(self):
        for el in self.connections:
            print(el, self.connections[el])


def get_data(filename: str):
    graph = Graph()
    with open(filename, 'r') as file:
        lines = file.readlines()
        n, m = tuple(int(x) for x in lines[0].split())
        clients = tuple(int(x) for x in lines[1].split())
        for line in lines[2:]:
            start, end, weight = tuple(int(x) for x in line.split())
            graph.create_edges_from_vertexes(start, end, weight)

    return n, m, clients, graph


def write_data(filename, data):
    data = str(data)
    with open(filename, 'w') as file:
        file.write(data)


def dejkstra_algorithm(graph: Graph, start_vertex_id: int):
    distances = {vertex_id: math.inf for vertex_id in graph.connections}

    distances[start_vertex_id] = 0

    if start_vertex_id not in graph.connections:
        raise ValueError('Not found start_vertex_id in_files graph vertexes')

    available_vertexes_queue = PriorityQueue()
    available_vertexes_queue.put((0, start_vertex_id))

    while not available_vertexes_queue.empty():
        parent_vertex = available_vertexes_queue.get()[1]

        for child_vertex_tuple in graph.connections[parent_vertex]:
            distance = distances[parent_vertex] + child_vertex_tuple[1]

            child_vertex_id = child_vertex_tuple[0]

            if distance < distances[child_vertex_id]:
                distances[child_vertex_id] = distance
                available_vertexes_queue.put((distance, child_vertex_id))

    return distances


def main():
    n, m, clients, graph = get_data('in_files/first_in')
    min_max_latency = None

    for vertex_id in graph.connections:

        if vertex_id not in clients:
            current_latencies = dejkstra_algorithm(graph, vertex_id)
            print(current_latencies)
            current_max_latency = max([current_latencies[client] for client in clients])
            if min_max_latency is None:
                min_max_latency = current_max_latency
            elif current_max_latency < min_max_latency:
                min_max_latency = current_max_latency

    write_data('out_files/first_out', min_max_latency)

    return min_max_latency


if __name__ == '__main__':
    print(main())
